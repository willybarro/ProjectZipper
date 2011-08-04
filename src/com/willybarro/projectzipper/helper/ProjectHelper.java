package com.willybarro.projectzipper.helper;

import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.ui.OpenProjects;

/**
 * Helper for working with netbeans project objects
 * 
 * @author Willy Barro
 */
abstract public class ProjectHelper {

    public static String getProjectSanitizedName(Project project) {
        String pName = ProjectHelper.getProjectName(project);

        // Sanitize
        pName = pName.toLowerCase().replace(" ", "-").replaceAll("/[^a-z0-9_-]/", "");

        return pName;
    }

    public static String getProjectAbsolutePath(Project project) {
        try {
            return project.getProjectDirectory().getPath();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public static ProjectInformation getProjectInfo(Project project) {
        return ProjectUtils.getInformation(project);
    }

    public static Project[] getProjectList() {
        return OpenProjects.getDefault().getOpenProjects();
    }
    
    public static Project getMainProject() {
        return OpenProjects.getDefault().getMainProject();
    }

    public static String getProjectName(Project project) {
        return ProjectUtils.getInformation(project).getDisplayName();
    }
}
