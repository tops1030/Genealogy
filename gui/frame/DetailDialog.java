package gui.frame;

import javax.swing.JDialog;
import javax.swing.JLabel;

import util.Node;

import java.awt.*;

public class DetailDialog extends JDialog {
    public DetailDialog(Node selectedNode){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        this.setSize(100,280);
        this.setTitle("查询结果");
        this.setLocationRelativeTo(null);
        Container container=this.getContentPane();
        container.setLayout(new GridLayout(8,1));
        container.add(new JLabel("姓名:"+selectedNode.getName(),JLabel.CENTER));
        if(selectedNode.getSex()==false) container.add(new JLabel("性别:男",JLabel.CENTER));
        else container.add(new JLabel("性别:女",JLabel.CENTER));
        container.add(new JLabel("出生地点:"+selectedNode.getBirthplace(),JLabel.CENTER));
        container.add(new JLabel("出生日期:"+selectedNode.getBirthday(),JLabel.CENTER));
        container.add(new JLabel("死亡日期:"+selectedNode.getDeathday(),JLabel.CENTER));
        container.add(new JLabel("身高:"+selectedNode.getHeight(),JLabel.CENTER));
        container.add(new JLabel("职业:"+selectedNode.getVocation(),JLabel.CENTER));
        if(selectedNode.getSpouse()!=null)
            container.add(new JLabel("配偶:"+selectedNode.getSpouse().getName(),JLabel.CENTER));
    }
}
