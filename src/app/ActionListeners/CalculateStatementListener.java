package app.ActionListeners;

import app.ProcessStatement;

import java.awt.event.*;
import javax.swing.JLabel;

/**
 * CalculateStatement
 */
public class CalculateStatementListener implements ActionListener{

    private JLabel statement;
    private JLabel numberOfVariables;

    public CalculateStatementListener(JLabel statement, JLabel numberOfVariables) {
        this.statement = statement;
        this.numberOfVariables = numberOfVariables;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!statement.getText().isBlank())
            new ProcessStatement(statement.getText(), Integer.parseInt(numberOfVariables.getText()));
    }
    
}