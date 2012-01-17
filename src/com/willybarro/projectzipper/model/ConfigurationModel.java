/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willybarro.projectzipper.model;

import com.willybarro.projectzipper.lib.IgnorePattern;
import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.TreePath;

/**
 * Configuration model for abstract packager
 * 
 * @author Willy Barro <willybarro@gmail.com>
 */
public final class ConfigurationModel {
    private TreePath[] checkedNodes;
    private ArrayList<IgnorePattern> ignorePattern;
    private File destination;
    
    /**
     * Configuration constructor.
     * 
     * @param checkedNodes 
     */
    public ConfigurationModel(TreePath[] checkedNodes) {
        this.setCheckedNodes(checkedNodes);
    }
    
    /**
     * Setter for checkedNodes
     */
    public void setCheckedNodes(TreePath[] checkedNodes) {
        this.checkedNodes = checkedNodes;
    }
    
    /**
     * Getter for checkedNodes
     */
    public TreePath[] getCheckedNodes() {
        return this.checkedNodes;
    }
}
