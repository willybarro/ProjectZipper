/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.willybarro.projectzipper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Build",
id = "com.willybarro.projectzipper.Wizard")
@ActionRegistration(displayName = "#CTL_Wizard")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1426)
})
@Messages("CTL_Wizard=Project Zipper")
public final class Wizard implements ActionListener {
    public static final String version = "0.9";

    @Override
    public void actionPerformed(ActionEvent e)
    {
        WizardController.getDefault().showViewAction();
    }
}
