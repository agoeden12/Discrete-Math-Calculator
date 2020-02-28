package app;

import app.ActionListeners.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class App {

    public static void main(String[] args) throws Exception {
        createFrame();
    }

    private static void calculateStatementPanels(JFrame frame, GridBagConstraints constraints, GridBagLayout layout) {
        // -------------------------------------------------------------------------------------------
        JPanel statementPanel = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        layout.setConstraints(statementPanel, constraints);
        frame.add(statementPanel);

        JLabel statementLabel = new JLabel("Statement:");
        statementPanel.add(statementLabel);

        JLabel statementText = new JLabel();
        statementText.setHorizontalAlignment(0);
        statementText.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"),
                "backspace");
        statementText.getActionMap().put("backspace", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (statementText.getText() != null && !statementText.getText().isEmpty())
                    statementText
                            .setText(statementText.getText().substring(0, statementText.getText().lastIndexOf(" ")));
            }
        });
        statementPanel.add(statementText);

        // -------------------------------------------------------------------------------------------
        JPanel calculatePanel = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 1;
        layout.setConstraints(calculatePanel, constraints);
        frame.add(calculatePanel);

        JButton calculateButton = new JButton("Calculate");
        calculatePanel.add(calculateButton);

        // -------------------------------------------------------------------------------------------
        JPanel connectivesPanel = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 2;
        layout.setConstraints(connectivesPanel, constraints);
        frame.add(connectivesPanel);

        JButton leftParentheses = new JButton("(");
        leftParentheses.addActionListener(new ChangeStatementListener(statementText, "("));
        connectivesPanel.add(leftParentheses);

        JButton rightParentheses = new JButton(")");
        rightParentheses.addActionListener(new ChangeStatementListener(statementText, ")"));
        connectivesPanel.add(rightParentheses);

        JButton notConnective = new JButton("~");
        notConnective.addActionListener(new ChangeStatementListener(statementText, "~"));
        connectivesPanel.add(notConnective);

        JButton andConnective = new JButton("AND");
        andConnective.addActionListener(new ChangeStatementListener(statementText, "AND"));
        connectivesPanel.add(andConnective);

        JButton orConnective = new JButton("OR");
        orConnective.addActionListener(new ChangeStatementListener(statementText, "OR"));
        connectivesPanel.add(orConnective);

        // -------------------------------------------------------------------------------------------
        JPanel variablesPanel = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 3;
        layout.setConstraints(variablesPanel, constraints);
        frame.add(variablesPanel);

        JLabel varCurrentLabel = new JLabel("Number of Variables:");
        varCurrentLabel.setHorizontalAlignment(0);

        JLabel varAmountLabel = new JLabel("1");
        varAmountLabel.setHorizontalAlignment(0);

        JButton subtractVarsButton = new JButton("-");
        subtractVarsButton.addActionListener(new ChangeVariableAmountListener(varAmountLabel, variablesPanel, false));

        JButton addVarsButton = new JButton("+");
        addVarsButton.addActionListener(new ChangeVariableAmountListener(varAmountLabel, variablesPanel, true));

        variablesPanel.add(varCurrentLabel);
        variablesPanel.add(varAmountLabel);
        variablesPanel.add(subtractVarsButton);
        variablesPanel.add(addVarsButton);

        JButton pVar = new JButton("P");
        pVar.addActionListener(new ChangeStatementListener(statementText, "p"));
        variablesPanel.add(pVar);

        JButton qVar = new JButton("Q");
        qVar.addActionListener(new ChangeStatementListener(statementText, "q"));
        variablesPanel.add(qVar);

        JButton rVar = new JButton("R");
        rVar.addActionListener(new ChangeStatementListener(statementText, "r"));
        variablesPanel.add(rVar);

        calculateButton.addActionListener(new CalculateStatementListener(statementText, varAmountLabel));

    }

    // private static void calculateCountingPanels(){}

    private static void createFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Statement Form Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        // -------------------------------------------------------------------------------------------
        calculateStatementPanels(frame, constraints, layout);

        // -------------------------------------------------------------------------------------------
        JSeparator separator = new JSeparator();
        constraints.gridx = 0;
        constraints.gridy = 4;
        layout.setConstraints(separator, constraints);
        separator.setOrientation(SwingConstants.HORIZONTAL);
        frame.add(separator);

        frame.setVisible(true);
    }
}