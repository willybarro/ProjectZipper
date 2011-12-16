package com.willybarro.projectzipper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

/**
 * Netbeans Action Class.
 * This class is the entrance to the plugin. Netbeans uses it to give an action
 * to the Zipper button on File > Project Zipper
 * 
 * @author Willy Barro
 * @link http://github.com/willybarro/
 */
@ActionID(category = "Build",
id = "com.willybarro.projectzipper.ProjectZipperAction")
@ActionRegistration(displayName = "#CTL_Wizard")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1426)
})
@Messages("CTL_Wizard=Project Zipper")
public final class ProjectZipperAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Starts the plugin
        ProjectZipper pj = new ProjectZipper();
    }
}
