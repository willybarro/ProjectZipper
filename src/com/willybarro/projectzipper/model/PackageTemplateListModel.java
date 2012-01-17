package com.willybarro.projectzipper.model;

import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;
import org.netbeans.api.project.Project;

/**
 * Package template list.
 * Every package template will come from this model.
 * 
 * @author Willy Barro <willybarro@gmail.com>
 */
public class PackageTemplateListModel extends DefaultListModel<PackageTemplate> {
    private boolean newTemplate = true;
    private File pjzDir;
    
    /**
     * Construct the model which holds the package templates.
     * This constructor gathers information about saved templates (if any).
     */
    public PackageTemplateListModel(Project activeProject) {
        this.addElement(new PackageTemplate("<new template>", null, true));
        
        String projectDirectory = activeProject.getProjectDirectory().getPath();
        this.pjzDir = new File(
            projectDirectory + File.separator +
            "nbproject" + File.separator + "ProjectZipper"
        );
        if(this.pjzDir.exists() && this.pjzDir.isDirectory()) {
            if(this.pjzDir.canRead()) {
                File[] pjzFiles = this.pjzDir.listFiles(new PJZPreferencesFileFilter());
                for(File templateFile : pjzFiles) {
                    this.addElement(new PackageTemplate(templateFile.getName(), templateFile, false));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Unable to read: " + this.pjzDir.getAbsolutePath() + "\nPlease, verify this directory permissions.");
            }
        }
    }
    
    /**
     * Saves template
     */
    public boolean saveTemplate(TreePath[] files, String fileName) {
        if(!this.pjzDir.canWrite()) {
            JOptionPane.showMessageDialog(null, "Unable to write on: " + this.pjzDir.getAbsolutePath() + "\nPlease, verify this directory permissions.");
            return false;
        }
            
        boolean pjzFileCreated = false;
        try {
            File pjzFile = new File(this.pjzDir, fileName + ".pjz");
            pjzFileCreated = pjzFile.createNewFile();
            
            if(pjzFileCreated) {
                this.addElement(new PackageTemplate(pjzFile.getName(), pjzFile, false));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return pjzFileCreated;
    }
    
    /**
     * Deletes a template from the list (and phisically from the hard disk)
     */
    public boolean deleteTemplate(PackageTemplate template) {
        File f = template.getFile();
        if(!f.canWrite()) {
            JOptionPane.showMessageDialog(null, "Permission denied while trying to remove:\n" + f.getAbsolutePath());
            return false;
        } else {
            return f.delete() && this.removeElement(template);
        }
    }
    
    /**
     * Returns if this template is the new item button.
     * @return 
     */
    public boolean isNewItemButton() {
        return this.newTemplate;
    }
}