package app.ActionListeners;

import javax.swing.*;
import java.awt.event.*;

/**
 * ChangeStatement
 */
public class ChangeVariableAmountListener implements ActionListener{

    private JLabel label;
    private JPanel panel;
    private boolean isAddition;

    public ChangeVariableAmountListener(JLabel label, JPanel panel, boolean isAddition) {
        this.label = label;
        this.isAddition = isAddition;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int currentNumber = Integer.parseInt(label.getText());
        if (isAddition && currentNumber < 3) {
            currentNumber++;
        } else if (!isAddition && currentNumber > 1) {
            currentNumber--;
        }

        label.setText("" + currentNumber);
        panel.validate();
        panel.repaint();
    }
    
}