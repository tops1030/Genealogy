package gui.panel;

import javax.swing.*;
import java.awt.*;

import gui.listener.*;
import util.MakeButton;

public class OperationPanel extends JPanel {
    private OperationPanel(){
        GridBagLayout layout=new GridBagLayout();
        GridBagConstraints constraints=new GridBagConstraints();
        this.setLayout(layout);
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx=0.0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        JLabel title=new JLabel("操作盘");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        layout.setConstraints(title,constraints);
        this.add(title);
        constraints.weightx=0.5;
        constraints.weighty=0.2;
        constraints.gridwidth=1;
        MakeButton.makebutton("添加后代",this,layout,constraints,new AddSonButtonListener());
        constraints.gridwidth=GridBagConstraints.REMAINDER;
        MakeButton.makebutton("添加先辈",this,layout,constraints,new AddFatherButtonListener());
        constraints.gridwidth=GridBagConstraints.REMAINDER;
        MakeButton.makebutton("删除此人",this,layout,constraints,new DeleteButtonListener());
        MakeButton.makebutton("查询",this,layout,constraints,new QueryButtonListener());
        MakeButton.makebutton("婚配",this,layout,constraints,new MarryButtonListener());
        this.setSize(250,300);
    }
    private static OperationPanel instance=new OperationPanel();
    public static OperationPanel getInstance(){return instance;}
}
