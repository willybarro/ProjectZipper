package com.willybarro.projectzipper.controller;

import com.willybarro.projectzipper.helper.ProjectHelper;
import com.willybarro.projectzipper.view.ProjectSelectionView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.netbeans.api.project.Project;

/**
 * Controller class related to the Project Selection Frame
 * 
 * @author Willy Barro
 */
public class ProjectSelectionController {
    static ProjectSelectionController instance = null;
    
    /**
     * Singleton
     * 
     * @return ProjectSelectionController
     */
    public static ProjectSelectionController getDefault()
    {
        if(instance == null) {
            instance = new ProjectSelectionController();
        }
        
        return instance;
    }
    
    /**
     * Shows Project List/View
     */
    public void showViewAction() {
        try {
            // Get list of projects and show a list with all projects
            Project[] projectList = ProjectHelper.getProjectList();
            if(projectList.length == 0) {
                throw new Exception("There are no opened projects.");
            } else {
                JFrame psv = new ProjectSelectionView(projectList);
                psv.setVisible(true);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
