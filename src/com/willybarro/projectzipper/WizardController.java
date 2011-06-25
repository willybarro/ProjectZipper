package com.willybarro.projectzipper;

import com.willybarro.swing.CheckboxedTreeFileChooser;
import java.awt.Component;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * Wizard class
 * 
 * @author Willy Barro
 */
public class WizardController {
    static WizardController instance = null;
    private CheckboxedTreeFileChooser ctfc;
    private WizardView view = null;
    
    private void ZipperController()
    {
    }
    
    public static WizardController getDefault()
    {
        if(instance == null) {
            instance = new WizardController();
        }
        
        return instance;
    }
    
    /**
     * Show main view
     */ 
    public void showViewAction()
    {
        try {
            // Gets main project path
            String mainProjectPath = ProjectHelper.getMainProjectAbsolutePath();
            
            if(mainProjectPath.equals("")) {
                // @todo give user a list with all active projects, so he/she can active one
                JOptionPane.showMessageDialog(null, "Please, select a project as your main project\nOn your project panel: Right click > Set Main Project");
            } else {
                // Build tree file chooser
                ctfc = new CheckboxedTreeFileChooser(mainProjectPath);
                JScrollPane panelFileTree = ctfc.getJScrollPane();

                // Build main frame
                view = new WizardView();
                Component compTreeFileChooser = view.add(panelFileTree);
                compTreeFileChooser.setBounds(10, 40, 500, 250);

                // Show main frame
                view.setVisible(true);
                
                // Sets view so the listener can change the active panel
                ZipperListener.setView(view);
            }
        } catch(java.io.IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void startBuildAction()
    {
        try {
            // Here start the export :)
            Zipper zipper = new Zipper();
            zipper.generateZip(
                    ctfc.getCheckedPaths(),
                    view.getIgnorePatterns(),
                    new File(view.getInputPath().getText()),
                    view.getCbExportZip().isSelected()
            );
            
            // Change button status to exporting...
            view.setBtnExportIsLoading(true);
            Zipper.setCancelButtonPressed(false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void stopBuildAction()
    {
        Zipper.stopCurrentTask();
    }
}