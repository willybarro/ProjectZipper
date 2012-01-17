package com.willybarro.projectzipper.packager;

import com.willybarro.projectzipper.exception.PackagingException;
import com.willybarro.projectzipper.model.AbstractPackager;
import com.willybarro.projectzipper.model.ConfigurationModel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
    
    /**
     * Returns template name.
     * @return 
     */
    @Override
    public String getTitle() {
        return "Clipboard";
    }

    /**
     * 
     * @return boolean
     */
    @Override
    public boolean generate() 
        throws PackagingException
    {
        if(this.configuration.getCheckedNodes() == null)
            throw new PackagingException("No files were checked.");
        
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
