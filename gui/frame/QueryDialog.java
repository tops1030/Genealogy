package gui.frame;

import gui.panel.MainPanel;
import util.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class QueryDialog extends JDialog {
    public QueryDialog(){
        this.setTitle("查询");
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        FlowLayout layout=new FlowLayout();
        JPanel contentpane=new JPanel();
        this.setContentPane(contentpane);
        this.setLayout(layout);
        JRadioButton son=new JRadioButton("查询子代");
        JRadioButton parent=new JRadioButton("查询父代");
        JRadioButton name=new JRadioButton("按姓名查询");
        ButtonGroup group=new ButtonGroup();
        group.add(son);
        group.add(parent);
        group.add(name);
        contentpane.add(son);
        contentpane.add(parent);
        contentpane.add(name);
        JTextField textField=new JTextField("在这里输入待查询人的姓名");
        contentpane.add(textField);
        JButton confirmButton=new JButton("确定");
        JDialog thisHandle=this;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=textField.getText();
                if(group.getSelection()==null){
                    JOptionPane.showMessageDialog(thisHandle,"未选择查询方式");
                    return;
                }
                int selection=0;
                for(Component c:thisHandle.getContentPane().getComponents()){
                    if(c instanceof JRadioButton){
                        if(((JRadioButton) c).isSelected()){
                            switch (((JRadioButton) c).getText()){
                                case "查询子代":selection=0;break;
                                case "查询父代":selection=1;break;
                                case "按姓名查询":selection=2;break;
                            }
                        }
                    }
                }
                //使用bfs查询
                LinkedList<DefaultMutableTreeNode> linkedList=new LinkedList<DefaultMutableTreeNode>();
                JTree genealogy=(JTree) ((JScrollPane) MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
                DefaultMutableTreeNode root=(DefaultMutableTreeNode) genealogy.getModel().getRoot();
                linkedList.offer(root);
                while (!linkedList.isEmpty()){
                    DefaultMutableTreeNode testNode=linkedList.poll();
                    if(testNode.getUserObject().toString().equals(name)){
                        switch (selection){
                            case 0:
                                for(int i=0;i<testNode.getChildCount();i++){
                                    new DetailDialog((Node)testNode.getChildAt(i)).setVisible(true);
                                }
                                break;
                            case 1:
                                if(((DefaultMutableTreeNode)testNode.getParent()).getUserObject().toString().equals("家谱")){
                                    JOptionPane.showMessageDialog(thisHandle,"此节点无父代");
                                    break;
                                }
                                new DetailDialog((Node)testNode.getParent()).setVisible(true);
                                break;
                            case 2:
                                new DetailDialog((Node)testNode).setVisible(true);
                                break;
                        }
                        return;
                    }
                    for(int i=0;i<testNode.getChildCount();i++){
                        linkedList.add((DefaultMutableTreeNode)testNode.getChildAt(i));
                    }
                }
                JOptionPane.showMessageDialog(thisHandle,"未找到此节点");
            }
        });
        contentpane.add(confirmButton);
    }
}
