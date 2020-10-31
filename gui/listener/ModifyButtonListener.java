package gui.listener;

import gui.frame.AddSonDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        new AddSonDialog().setVisible(true);
    }
}
