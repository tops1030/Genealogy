package gui.listener;

import gui.frame.MarryDialog;
import gui.panel.MainPanel;
import gui.panel.OperationPanel;
import util.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarryButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
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
        if(((Node)tempNode).getSpouse()!=null){
            JOptionPane.showMessageDialog(OperationPanel.getInstance(),"所选节点已拥有配偶,无法继续添加");
            return;
        }
        new MarryDialog().setVisible(true);
    }
}
