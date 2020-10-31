package gui.listener;

import gui.frame.AddSonDialog;
import gui.frame.QueryDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        new QueryDialog().setVisible(true);
    }
}
