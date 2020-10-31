package gui.panel;

import util.Node;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public class StatisticsPanel extends JPanel {
    private StatisticsPanel(){
        this.setSize(250,225);
        JTree genealogy=(JTree) ((JScrollPane)MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
        DefaultMutableTreeNode root=(DefaultMutableTreeNode) genealogy.getModel().getRoot();
        int aveHeight=0;
        int aveAge=0;
        int sonCount=0;
        //使用bfs遍历树节点
        LinkedList<DefaultMutableTreeNode> linkedList=new LinkedList<DefaultMutableTreeNode>();
        linkedList.offer(root);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        while (!linkedList.isEmpty()){
            DefaultMutableTreeNode testNode=linkedList.poll();
            if(!testNode.getUserObject().equals("家谱")){
                Node tempNode=(Node)testNode;
                aveHeight+=tempNode.getHeight();
                sonCount++;
                try {
                    bef.setTime(sdf.parse(tempNode.getBirthday()));
                    aft.setTime(sdf.parse(tempNode.getDeathday()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                aveAge+=aft.get(Calendar.YEAR)-bef.get(Calendar.YEAR);
            }
            if(testNode.isLeaf())continue;
            for(int i=0;i<testNode.getChildCount();i++){
                linkedList.add((DefaultMutableTreeNode) testNode.getChildAt(i));
            }
        }
        aveHeight/=sonCount;
        aveAge/=sonCount;
        JLabel lable=new JLabel("平均身高:"+aveHeight+"   平均寿命:"+aveAge+"   总人数:"+sonCount,JLabel.CENTER);
        this.add(lable,BorderLayout.CENTER);
    }
    public void upDate(){
        int aveHeight=0;
        int aveAge=0;
        int sonCount=0;
        //使用bfs遍历树节点
        LinkedList<DefaultMutableTreeNode> linkedList=new LinkedList<DefaultMutableTreeNode>();
        JTree genealogy=(JTree) ((JScrollPane)MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
        DefaultMutableTreeNode root=(DefaultMutableTreeNode) genealogy.getModel().getRoot();
        linkedList.offer(root);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        while (!linkedList.isEmpty()){
            DefaultMutableTreeNode testNode=linkedList.poll();
            if(!testNode.getUserObject().equals("家谱")){
                Node tempNode=(Node)testNode;
                aveHeight+=tempNode.getHeight();
                sonCount++;
                try {
                    bef.setTime(sdf.parse(tempNode.getBirthday()));
                    aft.setTime(sdf.parse(tempNode.getDeathday()));
                } catch (ParseException er) {
                    er.printStackTrace();
                }
                aveAge+=aft.get(Calendar.YEAR)-bef.get(Calendar.YEAR);
            }
            if(testNode.isLeaf())continue;
            for(int i=0;i<testNode.getChildCount();i++){
                linkedList.add((DefaultMutableTreeNode) testNode.getChildAt(i));
            }
        }
        if(sonCount!=0){
            aveHeight/=sonCount;
            aveAge/=sonCount;
        }
        else{
            aveHeight=aveAge=0;
        }
        JLabel label=(JLabel) this.getComponent(0);
        label.setText("平均身高:"+aveHeight+"   平均寿命:"+aveAge+"   总人数:"+sonCount);
    }
    private static StatisticsPanel instance=new StatisticsPanel();
    public static StatisticsPanel getInstance(){return instance;}
}
