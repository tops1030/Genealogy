package gui.listener;

import gui.frame.AddSonDialog;
import gui.panel.MainPanel;
import gui.panel.OperationPanel;
import gui.panel.StatisticsPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JTree genealogy=(JTree) ((JScrollPane) MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
        DefaultMutableTreeNode tempNode=(DefaultMutableTreeNode) genealogy.getLastSelectedPathComponent();
        if(tempNode==null) {
            JOptionPane.showMessageDialog(OperationPanel.getInstance(),"未选取节点");
            return;
        }
        if(tempNode.getUserObject().equals("家谱")){
            JOptionPane.showMessageDialog(OperationPanel.getInstance(),"所选节点无法进行此操作");
            return;
        }
        DefaultMutableTreeNode father=(DefaultMutableTreeNode) tempNode.getParent();
        for(int i=0;i<tempNode.getChildCount()+i;i++){
            father.add((DefaultMutableTreeNode)tempNode.getChildAt(0));
        }
        DefaultTreeModel model=(DefaultTreeModel)genealogy.getModel();
        tempNode.removeFromParent();
        StatisticsPanel.getInstance().upDate();
        genealogy.updateUI();
        JOptionPane.showMessageDialog(OperationPanel.getInstance(),"删除成功,被删除节点后代已自动移至其父节点");
    }
}
