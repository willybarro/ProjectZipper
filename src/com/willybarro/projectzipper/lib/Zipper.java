package com.willybarro.projectzipper.lib;

import com.willybarro.projectzipper.helper.ProjectHelper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.netbeans.api.project.Project;
import org.openide.execution.ExecutorTask;


/**
 *
 * @author Willy Barro
 */
public class Zipper {
    private TreePath[] checkedPaths;
    private ArrayList<IgnorePattern> ignorePatterns;
    private Project activeProject;
    private static boolean cancelButtonPressed = false;
    
    public Zipper(Project project) {
        this.activeProject = project;
    }
    
    /**
     * Export Task
     */
    private static ExecutorTask currentTask;
    public static void setCurrentTask(ExecutorTask task) {
        currentTask = task;
        Zipper.setCancelButtonPressed(false);
    }
    
    public static void stopCurrentTask()
    {
        if(Zipper.currentTask != null) {
            Zipper.currentTask.stop();
            Zipper.currentTask = null;
            Zipper.setCancelButtonPressed(true);
        }
    }
    
    public static void setCancelButtonPressed(boolean cancelButtonPressed) {
        Zipper.cancelButtonPressed = cancelButtonPressed;
    }
    
    public static boolean isCancelButtonPressed() {
        return Zipper.cancelButtonPressed;
    }
    
    /**
     * Export Destination
     */
    private static File destination;
    public static File getDestination() {
        return destination;
    }
    
    
    /**
     * Generates the export package. Here comes all the logic :)
     * 
     * @param cp Checked Nodes on the tree of files
     * @param ip Array of Ignore-Patterns
     * @param dest Destination to where the package will be exported to
     * @param isZip If true, will export to a zip file.
     */
    public void generateZip(TreePath[] cp, ArrayList<IgnorePattern> ip, File dest, boolean isZip)
            throws Exception
    {
        try {
            // Set class variables
            checkedPaths = cp;
            ignorePatterns = ip;
            destination = dest;
            
            // Validation
            if(checkedPaths == null) {
                throw new Exception("You should select at least one directory/file.");
            }
            if(destination.getName().trim().equals("")) {
                throw new Exception("You should inform an Export Location.");
            }
            if((!isZip && !destination.canWrite()) && (isZip && !destination.getParentFile().canWrite()))  {
                throw new Exception("Your export location isn't writable.");
            }
            if(isZip && !destination.getPath().toLowerCase().endsWith(".zip")) {
                throw new Exception("Your export location must end with \".zip\".");
            }
            if(destination.exists()) {
                String dirFile = destination.isFile() ? "file location" : "directory";
                throw new Exception("The "+ dirFile +" you choosed already exists.\nPlease, choose a different one.");
            }

            // Builds ANT build.xml file
            Document antDoc = buildAntDocument(isZip);
            
            // Transform to XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            File antf = (File) File.createTempFile(".tmpant", "xml");
            StreamResult result = new StreamResult(antf);
            DOMSource source = new DOMSource(antDoc);
            transformer.transform(source, result);

            // Executes ant over temporary build file
            FileObject antFileObject = FileUtil.toFileObject(FileUtil.normalizeFile(antf));
            ExecutorTask antTask = ActionUtils.runTarget(antFileObject, new String[]{"do-export"}, null);
            antTask.addTaskListener(new ZipperListener());
            setCurrentTask(antTask);

            antf.deleteOnExit();
        } catch(TransformerException e) {
            JOptionPane.showMessageDialog(null, "There was an error generating your build. Please, try again.");
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error writing your build. Please, try again.");
        }
    }
    
    private Document buildAntDocument(boolean isZip)
    {
        try {
            // Creates DOM Document
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("project");
            root.setAttribute("name", ProjectHelper.getProjectName(activeProject));
            root.setAttribute("default", "do-export");
            root.setAttribute("basedir", ProjectHelper.getProjectAbsolutePath(activeProject));
            doc.appendChild(root);

            Element target = doc.createElement("target");
            target.setAttribute("name", "do-export");

            if(isZip) {
                buildAntZipTask(target);
            } else {
                buildAntCopyTask(target);
            }

            root.appendChild(target);
            
            return doc;
        } catch(ParserConfigurationException e) {
            JOptionPane.showMessageDialog(null, "There was an error generating your build. Please, try again. (ParserConfigurationException)");
            
            return null;
        }
    }
    
    /**
     * Creates ANT Zip Task
     * 
     * @param root 
     */
    private void buildAntZipTask(Element root)
    {
        // Create ZIP ant task
        Document doc = root.getOwnerDocument();
        Element zip = doc.createElement("zip");
        zip.setAttribute("destfile", destination.getAbsolutePath());
        
        // Builds selected nodes fileset
        buildAntFileSet(zip);
        
        root.appendChild(zip);
    }
    
    /**
     * Creates ANT Copy Task
     * 
     * @param root Root node which fileset will be inserted to
     */
    private void buildAntCopyTask(Element root)
    {
        Document doc = root.getOwnerDocument();
        Element copy = doc.createElement("copy");
        copy.setAttribute("todir", destination.getAbsolutePath());
        
        // Builds selected nodes fileset
        buildAntFileSet(copy);
        
        root.appendChild(copy);
    }
    
    /**
     * Transforms TreePath selected nodes into an ANT fileset
     * 
     * @param root Root node which fileset will be inserted to
     */
    private void buildAntFileSet(Element root)
    {
        String projectAbsolutePath = ProjectHelper.getProjectAbsolutePath(activeProject);
        Document doc = root.getOwnerDocument();
        Element fileset = doc.createElement("fileset");
        fileset.setAttribute("defaultexcludes", "no");
        
        // Include files (to create)
        String includePattern = "";
        for(TreePath c : checkedPaths) {
            File p = (File) c.getLastPathComponent();
            Element include = doc.createElement("include");
            
            // Remove root path. On build.xml it must be relative to fileset dir
            includePattern = p.getAbsolutePath()
                    .replace("\\", "/")
                    .replace(projectAbsolutePath, "");
            
            // If it's a directory, we'll get all files inside
            if(p.isDirectory()) {
                includePattern += "/**";
            }
            
            // Remove first "/", if we have it...
            if(includePattern.charAt(0) == '/') {
                includePattern = includePattern.substring(1, includePattern.length());
            }
            
            include.setAttribute("name", includePattern);
            fileset.appendChild(include);
        }
        
        // Exclude files (to ignore)
        String ignorePattern = "";
        for(IgnorePattern ig : ignorePatterns) {
            if(ig != null) {
                Element excl = doc.createElement("exclude");
            
                switch(ig.type) {
                    case Ignore.IGNORE_DIRECTORY:
                        ignorePattern = "**/" + ig.pattern + "/**";
                        break;
                    case Ignore.IGNORE_FILE:
                        ignorePattern = "**/" + ig.pattern;
                        break;
                    case Ignore.IGNORE_BOTH:
                    default:
                        ignorePattern = "**/" + ig.pattern;
                        break;
                }

                excl.setAttribute("name", ignorePattern);
                fileset.appendChild(excl);
            }
        }
        
        // Create XML includes and excludes
        fileset.setAttribute("dir", projectAbsolutePath);
        root.appendChild(fileset);
    }
}
