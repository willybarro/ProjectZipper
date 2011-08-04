package com.willybarro.projectzipper;

import com.willybarro.projectzipper.controller.ProjectSelectionController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.Exceptions;
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
id = "com.willybarro.projectzipper.WizardNBAction")
@ActionRegistration(displayName = "#CTL_Wizard")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1426)
})
@Messages("CTL_Wizard=Project Zipper")
public final class WizardNBAction implements ActionListener {
    public static final String version = "0.92";

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            ProjectSelectionController.getDefault().showViewAction();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
