/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willybarro.projectzipper.packager;

import com.willybarro.projectzipper.model.ConfigurationModel;
import javax.swing.tree.TreePath;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Willy Barro <willybarro@gmail.com>
 */
public class ClipboardPackagerTest {

    /**
     * Test of generate method, of class ClipboardPackager.
     */
    @Test
    public void testGenerate() {
        TreePath[] treePath = new TreePath[1];
        treePath[0] = new TreePath(new String[]{"Lorem", "Ipsum", "Dolor"});
        new ClipboardPackager(new ConfigurationModel(treePath));
        
    }
}
