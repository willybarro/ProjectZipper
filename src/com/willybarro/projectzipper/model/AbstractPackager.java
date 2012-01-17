package com.willybarro.projectzipper.model;

import com.willybarro.projectzipper.exception.PackagingException;
import java.io.File;

/**
 * Abstract packager.
 * Every packager must extend this class.
 * This class defines the standard contract for pluggable packagers.
 * 
 * @author Willy Barro <willybarro@gmail.com>
 */
public abstract class AbstractPackager {
    protected ConfigurationModel configuration;
    
    /**
     * Constructs the packager with a configuration model.
     * @param cm Model with all the needed configurations.
     */
    public AbstractPackager(ConfigurationModel cm) {
        this.configuration = cm;
    }
    
    /**
     * Implements the logic which will generate the package
     */
    public abstract boolean generate() throws PackagingException;
    
    /**
     * Returns package name.
     * @return String Package name
     */
    public abstract String getTitle();
    
    /**
     * Treats path name, if one needs to add an extension or change the
     * file name, here's the place.
     * 
     * @param directory
     * @param fileName Untreated filename
     * @return 
     */
    public String inputPathTreatment(File directory, String fileName) {
        return "";
    }
    
    /**
     * Returns the plugin/packager title.
     * @return 
     */
    @Override
    public String toString() {
        return this.getTitle();
    }
}
