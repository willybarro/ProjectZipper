package com.willybarro.projectzipper.packager;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import javax.swing.tree.TreePath;

/**
 * ClipboardPackager class. This class saves the file list to the clipboard.
 * 
 * @author Willy Barro
 */
public class ClipboardPackager extends AbstractPackager {
    public ClipboardPackager(ConfigurationModel configuration) {
        super(configuration);
    }

    @Override
    public boolean generate() {
        if(this.configuration.getCheckedNodes() == null)
            return false;
        
        StringBuilder fileList = new StringBuilder();
        
        for(TreePath treePath : this.configuration.getCheckedNodes()) {
            fileList.append(treePath.getLastPathComponent().toString()).append("\n");
        }
        
        // Copy to clipboard
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(new StringSelection(fileList.toString()), null);
        
        return true;
    }
}
