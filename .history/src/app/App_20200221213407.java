package app;

import app.ActionListeners.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class App {

    private static enum Connectives {
        PARENTHESES, TILDE, AND, OR, CONDITIONAL, BICONDITIONAL
    }

    private static EnumMap<Connectives, List<String>> connectiveMap = new EnumMap<Connectives, List<String>>(Connectives.class);

    public static void main(String[] args) throws Exception {
        createFrame();
        initMap();
        
        connectiveMap.forEach((key,value) -> {
            System.out.println(String.format("Key: %s || Value: %s", key, value));
        });
    }

    private static void createFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Statement Form Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 250);
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

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
        statementText.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "backspace");
        statementText.getActionMap().put("backspace", new AbstractAction(){
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (statementText.getText() != null && !statementText.getText().isEmpty())
                    statementText.setText(statementText.getText().substring(0, statementText.getText().lastIndexOf(" ")));
            }
        });
        statementPanel.add(statementText);

        
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
        
        JButton biConditionalConnective = new JButton("IFF");
        biConditionalConnective.addActionListener(new ChangeStatementListener(statementText, "IFF"));
        
        JButton conditionalConnective = new JButton("IF");
        conditionalConnective.addActionListener(new ChangeStatementListener(statementText, "IF"));

        connectivesPanel.add(orConnective);
        connectivesPanel.add(conditionalConnective);
        connectivesPanel.add(biConditionalConnective);

        // -------------------------------------------------------------------------------------------
        JPanel variablesPanel = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 3;
        layout.setConstraints(variablesPanel, constraints);
        frame.add(variablesPanel);

        JButton pVar = new JButton("P");
        pVar.addActionListener(new ChangeStatementListener(statementText, "p"));
        variablesPanel.add(pVar);

        JButton qVar = new JButton("Q");
        qVar.addActionListener(new ChangeStatementListener(statementText, "q"));
        variablesPanel.add(qVar);
        
        // -------------------------------------------------------------------------------------------

        frame.setVisible(true);
    }

    private static void initMap(){
        connectiveMap.put(Connectives.PARENTHESES, Arrays.asList("(",")"));
        connectiveMap.put(Connectives.TILDE, Arrays.asList("~"));
        connectiveMap.put(Connectives.AND, Arrays.asList("AND"));
        connectiveMap.put(Connectives.OR, Arrays.asList("OR"));
        connectiveMap.put(Connectives.CONDITIONAL, Arrays.asList("IF"));
        connectiveMap.put(Connectives.BICONDITIONAL, Arrays.asList("IFF"));
    }

    private static void deMorganSwitch(boolean var1, boolean var2, Connectives connectives) {
    }
}