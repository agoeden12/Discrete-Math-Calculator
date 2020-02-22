package app.ActionListeners;
import javax.swing.*;
import java.awt.event.*;

/**
 * ChangeStatement
 */
public class ChangeStatementListener implements ActionListener{

    private String input;
    private JLabel label;

    public ChangeStatementListener(JLabel label, String input) {
        this.input = input;
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newText = (label.getText() == null) ? input : label.getText() + " " + input;
        label.setText(newText);
    }
    
}