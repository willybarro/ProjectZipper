package com.willybarro.projectzipper.model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * Package template list.
 * Every package template will come from this model.
 * 
 * @author Willy Barro <willybarro@gmail.com>
 */
public class PackageTemplateListModel extends AbstractListModel {
    private ArrayList<PackageTemplate> packageTemplateList = new ArrayList<PackageTemplate>();
    
    /**
     * Construct the model which holds the package templates.
     * This constructor gathers information about saved templates.
     */
    public PackageTemplateListModel() {
        // NBPreferences something something dark side
    }
    
    @Override
    public int getSize() {
        return this.packageTemplateList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.packageTemplateList.get(index);
    }
}