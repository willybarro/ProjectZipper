package com.willybarro.projectzipper.model;

import java.io.File;

/**
 * Packate template
 * @author Willy Barro <willybarro@gmail.com>
 */
public class PackageTemplate {
    private String title;
    private File file;
    private boolean isSaved = false;
    private boolean isNewItemButton = false;
    
    public PackageTemplate(String title, File file, boolean isNewItemButton) {
        this.title = title;
        this.file = file;
        this.isNewItemButton = isNewItemButton;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public boolean isSaved() {
        return this.isSaved;
    }
    
    public boolean isNewItemButton() {
        return this.isNewItemButton;
    }
    
    @Override
    public String toString() {
        return this.getTitle();
    }
}
