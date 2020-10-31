package util;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

public class MakeButton {
    public static void makebutton(String title, JPanel panel, GridBagLayout gridBagLayout, GridBagConstraints constraints, ActionListener listener){
        JButton button=new JButton(title);    //创建Button对象
        gridBagLayout.setConstraints(button,constraints);
        button.addActionListener(listener);
        panel.add(button);
    }
}
