package com.willybarro.projectzipper.view;

import com.willybarro.projectzipper.helper.ProjectHelper;
import com.willybarro.projectzipper.model.ProjectSelectionModel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Willy G. M. Barro Raffel
 */
public class ProjectSelectionCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        ProjectSelectionModel model = (ProjectSelectionModel) list.getModel();
        Object projeto = model.get(index);
        
        // If the cell/project is the active project, make it bold and selected
        if(projeto == ProjectHelper.getMainProject()) {
            c.setFont(c.getFont().deriveFont(Font.BOLD));
            
            if(list.getSelectedIndex() == -1) {
                list.setSelectedIndex(index);
            }
        } else {
            c.setFont(c.getFont().deriveFont(Font.PLAIN));
        }
        return c;
    }
}