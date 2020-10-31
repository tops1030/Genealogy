package gui.listener;

import gui.frame.*;
import gui.panel.MainPanel;
import gui.panel.OperationPanel;
import util.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSonButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        JTree genealogy=(JTree) ((JScrollPane)MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
        if(genealogy.getLastSelectedPathComponent()==null){
            JOptionPane.showMessageDialog(OperationPanel.getInstance(),"未选择节点");
            return;
        }
        new AddSonDialog().setVisible(true);
    }
}
