package com.willybarro.projectzipper.lib;

import com.willybarro.projectzipper.ui.WizardView;
import javax.swing.JOptionPane;
import org.openide.util.Task;
import org.openide.util.TaskListener;

/**
 *
 * @author Willy Barro
 */
public class ZipperListener implements TaskListener {
    private static WizardView view;
    public static void setView(WizardView view) {
        ZipperListener.view = view;
    }

    @Override
    public void taskFinished(Task task) {
        if(Zipper.isCancelButtonPressed()) {
            JOptionPane.showMessageDialog(null, "Packaging cancelled by user.");
        } else {
            JOptionPane.showMessageDialog(null, "Package sucessfully generated at:\n" + Zipper.getDestination().getAbsolutePath());
        }
        Zipper.setCancelButtonPressed(false);
        task.removeTaskListener(this);
        
        // Re-enable buttons
        view.setIsExporting(false);
    }
    
    
    
}
