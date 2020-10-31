package gui.panel;

import gui.frame.DetailDialog;
import gui.frame.MainFrame;
import util.Node;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FamilyMemberInformationPanel extends JPanel{
    private FamilyMemberInformationPanel(){
        JList detailList=new JList();
        DefaultListModel detailListModel=new DefaultListModel();
        detailList.setModel(detailListModel);
        this.add(detailList);
        this.setSize(250,150);
        detailList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()<2)return;
                int selection=detailList.getLeadSelectionIndex();
                if(selection!=detailListModel.getSize()-1)return;
                if(((Node) ((JTree) ((JScrollPane) MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0)).getLastSelectedPathComponent()).getSpouse()!=null)
                    new DetailDialog(((Node) ((JTree) ((JScrollPane) MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0)).getLastSelectedPathComponent()).getSpouse()).setVisible(true);
            }
        });
    }
    private static FamilyMemberInformationPanel instance=new FamilyMemberInformationPanel();
    public static FamilyMemberInformationPanel getInstance(){return instance;}
}
