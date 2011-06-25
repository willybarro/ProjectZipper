package com.willybarro.projectzipper;

import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.ui.OpenProjects;

/**
 *
 * @author Willy Barro
 */
public class ProjectHelper {
    public static String getMainProjectName()
    {
        ProjectInformation projectInfo = ProjectHelper.getMainProjectInfo();
        return projectInfo.getDisplayName();
    }
    
    public static String getMainProjectSanitizedName()
    {
        String pName = ProjectHelper.getMainProjectName();
        
        // Sanitize
        pName = pName.toLowerCase()
                .replace(" ", "-")
                .replaceAll("/[^a-z0-9_-]/", "");
        
        return pName;
    }
    
    public static String getMainProjectAbsolutePath()
    {   
        try {
            return ProjectHelper.getMainProject().getProjectDirectory().getPath();
        } catch(NullPointerException e) {
            return "";
        }
    }
    
    public static Project getMainProject()
    {
        Project project = OpenProjects.getDefault().getMainProject();
        
        return project;
    }
    
    public static ProjectInformation getMainProjectInfo()
    {
        return ProjectUtils.getInformation(ProjectHelper.getMainProject());
    }
}
