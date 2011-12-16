package com.willybarro.projectzipper;

import com.willybarro.projectzipper.controller.ProjectSelectionController;
import org.openide.modules.ModuleInfo;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 * ProjectZipper Main Class.
 * Meant to bootstrap the module.
 * 
 * @author Willy Barro
 * @link http://github.com/willybarro/
 */
public class ProjectZipper {
    public static String version;
    
    /**
     * Constructor/Bootstraps the model
     */
    public ProjectZipper() {
        try {
            this.collectMetadata();
            ProjectSelectionController.getDefault().showViewAction();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    /**
     * Gather current module metadata
     */
    private void collectMetadata() {
        for(ModuleInfo mo : Lookup.getDefault().lookupAll(ModuleInfo.class)) {
            if(mo.owns(ProjectZipper.class)) {
                ProjectZipper.version = mo.getSpecificationVersion().toString();
            }
        }
    }
}
