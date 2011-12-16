package com.willybarro.projectzipper.model;

import com.willybarro.projectzipper.helper.ProjectHelper;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;

/**
 * 
 * @author Willy G. M. Barro Raffel
 */
public class ProjectSelectionModel extends DefaultListModel {
    private ArrayList<Project> projects = new ArrayList<Project>();
    
    @Override
    public Object getElementAt(int i) {
        return ProjectUtils.getInformation(projects.get(i)).getDisplayName();
    }
    
    @Override
    public int getSize() {
        return projects.size();
    }
    
    public void addElement(Project e) {
        projects.add(e);
    }
    
    @Override
    public Project get(int i) {
        return projects.get(i);
    }
    
}
