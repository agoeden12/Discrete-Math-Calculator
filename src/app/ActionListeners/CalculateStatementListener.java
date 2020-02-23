package app.ActionListeners;

import app.ProcessStatement;

import java.awt.event.*;
import javax.swing.JLabel;

/**
 * CalculateStatement
 */
public class CalculateStatementListener implements ActionListener{

    private JLabel statement;

    public CalculateStatementListener(JLabel statement) {
        this.statement = statement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!statement.getText().isBlank())
            new ProcessStatement(statement.getText());
    }
    
}