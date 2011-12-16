/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willybarro.projectzipper.packager;

/**
 * Abstract packager.
 * Every packager must extend this class.
 * This class defines the standard contract for compatible packagers.
 * 
 * @author Willy Barro <willybarro@gmail.com>
 */
public abstract class AbstractPackager {
    ConfigurationModel configuration;
    
    public AbstractPackager(ConfigurationModel cm) {
        this.configuration = cm;
    }
    
    public String title() {
        return "";
    }
    
    /**
     * Implements the logic which will generate the package
     */
    public abstract boolean generate();
}
