package gui.listener;

import gui.frame.AddFatherDialog;
import gui.frame.AddSonDialog;
import gui.panel.MainPanel;
import gui.panel.OperationPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFatherButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        JTree genealogy=(JTree) ((JScrollPane) MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
        if(genealogy.getLastSelectedPathComponent()==null){
            JOptionPane.showMessageDialog(OperationPanel.getInstance(),"未选择节点");
            return;
        }
        DefaultMutableTreeNode tempNode=(DefaultMutableTreeNode) genealogy.getLastSelectedPathComponent();
        if(tempNode.getUserObject().equals("家谱")){
            JOptionPane.showMessageDialog(OperationPanel.getInstance(),"所选节点无法进行此操作");
            return;
        }
        new AddFatherDialog().setVisible(true);
    }
}
