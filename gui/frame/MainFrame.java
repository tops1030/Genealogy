package gui.frame;

import gui.panel.FamilyMemberInformationPanel;
import gui.panel.MainPanel;
import gui.panel.OperationPanel;
import gui.panel.StatisticsPanel;
import util.IOUtil;
import util.Pair;
import util.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainFrame extends JFrame {
    private MainFrame(){
        this.setSize(500,450);
        this.setTitle("家谱管理系统");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        MainPanel.getInstance().setBounds(0,0,250,225);
        FamilyMemberInformationPanel.getInstance().setBounds(225,0,250,225);
        StatisticsPanel.getInstance().setBounds(0,275,250,225);
        OperationPanel.getInstance().setBounds(250,225,250,225);
        OperationPanel.getInstance().setSize(200,150);
        this.add(MainPanel.getInstance());
        this.add(FamilyMemberInformationPanel.getInstance());
        this.add(StatisticsPanel.getInstance());
        this.add(OperationPanel.getInstance());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JTree genealogy=(JTree) ((JScrollPane)MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
                LinkedList<DefaultMutableTreeNode> linkedList=new LinkedList<DefaultMutableTreeNode>();
                DefaultMutableTreeNode root=(DefaultMutableTreeNode) genealogy.getModel().getRoot();
                linkedList.offer(root);
                ArrayList<String> src=new ArrayList<String>();
                ArrayList<Pair> pairs=new ArrayList<Pair>();
                int count=0,id=-1;
                while (!linkedList.isEmpty()){
                    id++;
                    DefaultMutableTreeNode testNode=linkedList.poll();
                    if(!testNode.getUserObject().toString().equals("家谱")){
                        Node node=(Node)testNode;
                        src.add(node.getName());
                        src.add(Boolean.toString(node.getSex()));
                        src.add(node.getBirthplace());
                        src.add(node.getBirthday());
                        src.add(node.getDeathday());
                        src.add(Integer.toString(node.getHeight()));
                        src.add(node.getVocation());
                        if(node.getSpouse()!=null){
                            src.add("true");
                            Node spouse=node.getSpouse();
                            src.add(spouse.getName());
                            src.add(Boolean.toString(spouse.getSex()));
                            src.add(spouse.getBirthplace());
                            src.add(spouse.getBirthday());
                            src.add(spouse.getDeathday());
                            src.add(Integer.toString(spouse.getHeight()));
                            src.add(spouse.getVocation());
                        }
                        else src.add("false");
                    }
                    for (int i=0;i<testNode.getChildCount();i++){
                        count++;
                        pairs.add(new Pair(id,count));
                        linkedList.add((DefaultMutableTreeNode) testNode.getChildAt(i));
                    }
                }
                src.add(0,Integer.toString(count));
                count=0;
                int sign=src.size();
                for(int i=0;i<pairs.size();i++){
                    if(pairs.get(i).getFirst()==0)continue;
                    count++;
                    src.add(Integer.toString(pairs.get(i).getFirst()-1));
                    src.add(Integer.toString(pairs.get(i).getSecond()-1));
                }
                src.add(sign,Integer.toString(count));
                String[] srcArray=src.toArray(new String[src.size()]);
                IOUtil.write(srcArray,"data.txt");
            }
        });
    }
    private static MainFrame instance=new MainFrame();
    public static MainFrame getInstance(){ return instance;}
    //使用单件模式确保只有一个MainFrame实例

    public static void main(String[] args) {
        instance.setVisible(true);
    }
}
