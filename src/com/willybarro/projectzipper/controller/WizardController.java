package com.willybarro.projectzipper.controller;

import com.willybarro.projectzipper.lib.Zipper;
import com.willybarro.projectzipper.lib.ZipperListener;
import com.willybarro.projectzipper.view.WizardView;
import com.willybarro.swing.CheckboxedTreeFileChooser;
import java.io.File;
import javax.swing.JOptionPane;
import org.netbeans.api.project.Project;

/**
 * Controller class related to the Zipper Wizard View
 * 
 * @author Willy Barro
 */
public class WizardController {

    static WizardController instance;
    private CheckboxedTreeFileChooser ctfc;
    private WizardView view;
    private Project activeProject;

    /**
     * Singleton
     * 
     * @return ProjectSelectionController
     */
    public static WizardController getDefault() {
        if(instance == null) {
            instance = new WizardController();
        }

        return instance;
    }

    /**
     * Show Zipper Wizard view.
     */
    public void showViewAction(Project project) {
        this.activeProject = project;
        
        // Build main frame
        view = new WizardView(project);
        ctfc = view.getCheckboxedTreeFileChooser();

        // Show main frame
        view.setVisible(true);

        // Sets view so the listener can change the active panel
        ZipperListener.setView(view);
    }

    /**
     * Starts the build process
     */
    public void startBuildAction() {
        try {
            // Here start the export :)
            Zipper zipper = new Zipper(activeProject);
            zipper.generateZip(
                    ctfc.getCheckedPaths(),
                    view.getIgnorePatterns(),
                    new File(view.getInputPath().getText()),
                    view.getCbExportZip().isSelected());

            // Change button status to exporting...
            Zipper.setCancelButtonPressed(false);
            view.setIsExporting(true);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Stops the build action
     */ 
    public void stopBuildAction() {
        Zipper.stopCurrentTask();
    }
}