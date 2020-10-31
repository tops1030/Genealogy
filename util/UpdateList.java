package util;

import gui.panel.FamilyMemberInformationPanel;
import gui.panel.MainPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class UpdateList {
    static public void updateList(){
        JTree genealogy=(JTree) ((JScrollPane) MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
        DefaultMutableTreeNode tempNode=(DefaultMutableTreeNode) genealogy.getLastSelectedPathComponent();
        if((tempNode.getUserObject()).equals(new String("家谱")))return;//排除选取结点为根节点的情况
        Node selectedNode=(Node)tempNode;
        JList detailList=(JList) FamilyMemberInformationPanel.getInstance().getComponent(0);
        DefaultListModel detailListModel=(DefaultListModel) detailList.getModel();
        detailListModel.removeAllElements();
        detailListModel.addElement("姓名:"+selectedNode.getName());
        if(selectedNode.getSex()==false) detailListModel.addElement("性别:男");
        else detailListModel.addElement("性别:女");
        detailListModel.addElement("出生地点:"+selectedNode.getBirthplace());
        detailListModel.addElement("出生日期:"+selectedNode.getBirthday());
        if(!selectedNode.getDeathday().equals("-1")) detailListModel.addElement("死亡日期:"+selectedNode.getDeathday());
        detailListModel.addElement("身高:"+selectedNode.getHeight()+"cm");
        detailListModel.addElement("职业:"+selectedNode.getVocation());
        if(selectedNode.getSpouse()!=null){
            detailListModel.addElement("是否已婚:是");
            detailListModel.addElement("配偶:"+selectedNode.getSpouse().getName());
        }
        else detailListModel.addElement("是否已婚:否");
    }
}
