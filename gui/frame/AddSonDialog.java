package gui.frame;

import gui.panel.MainPanel;
import gui.panel.StatisticsPanel;
import util.IsNumeric;
import util.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddSonDialog extends JDialog {
    public AddSonDialog(){
        this.setTitle("为当前家庭成员添加一个后代");
        this.setSize(200,300);
        this.setLocationRelativeTo(null);
        FlowLayout layout=new FlowLayout();
        JPanel contentpane=new JPanel();
        this.setContentPane(contentpane);
        this.setLayout(layout);
        JLabel nameLabel=new JLabel("姓名");
        contentpane.add(nameLabel);
        JTextField nameField=new JTextField(12);
        contentpane.add(nameField);
        JLabel sexLabel=new JLabel("性别");
        contentpane.add(sexLabel);
        JTextField sexField=new JTextField(12);
        contentpane.add(sexField);
        JLabel birthPlaceLabel=new JLabel("出生地点");
        contentpane.add(birthPlaceLabel);
        JTextField birthPlaceField=new JTextField(12);
        contentpane.add(birthPlaceField);
        JLabel birthDateLabel=new JLabel("出生日期");
        contentpane.add(birthDateLabel);
        JTextField birthDateField=new JTextField(12);
        contentpane.add(birthDateField);
        JLabel deathDateLabel=new JLabel("死亡日期");
        contentpane.add(deathDateLabel);
        JTextField deathDateField=new JTextField(12);
        contentpane.add(deathDateField);
        JLabel heightLabel=new JLabel("身高");
        contentpane.add(heightLabel);
        JTextField heightField=new JTextField(12);
        contentpane.add(heightField);
        JLabel vocationLabel=new JLabel("职业");
        contentpane.add(vocationLabel);
        JTextField vocationField=new JTextField(12);
        contentpane.add(vocationField);
        JButton confirmButton=new JButton("确定");
        JDialog thisHandle=this;
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTree genealogy=(JTree) ((JScrollPane)MainPanel.getInstance().getComponent(0)).getViewport().getComponent(0);
                DefaultMutableTreeNode tempNode=(DefaultMutableTreeNode) genealogy.getLastSelectedPathComponent();
                String name=nameField.getText();
                String sex=sexField.getText();
                String birthPlace=birthPlaceField.getText();
                String birthDate=birthDateField.getText();
                String deathDate=deathDateField.getText();
                String height=heightField.getText();
                String vocation=vocationField.getText();
                if((!sex.equals("男")&&!sex.equals("女"))||!IsNumeric.isNumeric(height)){
                    JOptionPane.showMessageDialog(thisHandle,"无效输入：性别只能选择男/女;身高只能输入数字");
                    return;
                }
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                boolean flag=false;
                try {
                    sdf.parse(birthDate);
                    sdf.parse(deathDate);
                } catch (ParseException parseException) {
                    JOptionPane.showMessageDialog(thisHandle,"日期格式错误,正确格式:yyyy-MM-dd");
                    parseException.printStackTrace();
                    flag=true;
                }
                if(flag==true){
                    thisHandle.dispose();
                    return;
                }
                if(sex.equals("男")) sex="false";
                    else sex="true";
                Node newNode=new Node(name,Boolean.parseBoolean(sex),birthPlace,birthDate,deathDate,Integer.parseInt(height),vocation);
                newNode.setUserObject(name);
                DefaultTreeModel model=(DefaultTreeModel)genealogy.getModel();
                model.insertNodeInto(newNode,tempNode,0);
                StatisticsPanel.getInstance().upDate();
                thisHandle.dispose();
            }
        });
        contentpane.add(confirmButton);
    }
}
