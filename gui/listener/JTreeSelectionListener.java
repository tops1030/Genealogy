package gui.listener;
import gui.panel.FamilyMemberInformationPanel;
import gui.panel.MainPanel;
import util.Node;
import util.UpdateList;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeSelectionListener implements TreeSelectionListener {
    public void valueChanged(TreeSelectionEvent e){
        UpdateList.updateList();
    }
}
