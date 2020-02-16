package app;

import javax.swing.*;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class App {

    private static enum Connectives {

        PARENTHESES, TILDE, AND, OR, CONDITIONAL, BICONDITIONAL

    }

    private static EnumMap<Connectives, List<String>> connectiveMap = new EnumMap<Connectives, List<String>>(
            Connectives.class);

    public static void main(String[] args) throws Exception {

        createFrame();

        initMap();

        connectiveMap.forEach((key, value) -> {

            System.out.println(String.format("Key: %s || Value: %s", key, value));

        });

    }

    private static void createFrame() {
        JFrame frame = new JFrame("Statement Form Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 250);
        // frame.setLayout(new BorderL());
        frame.setVisible(true);

        // JPanel statementPanel = new JPanel();
        // frame.add(statementPanel);
        JTextArea statementText = new JTextArea("TEST");
        // statementPanel.add(statementText);

        JPanel connectivesPanel = new JPanel();
        frame.add(connectivesPanel);
        connectivesPanel.add(statementText);

        JButton leftParentheses = new JButton("(");
        JButton rightParentheses = new JButton(")");
        JButton notConnective = new JButton("~");
        JButton andConnective = new JButton("AND");
        JButton orConnective = new JButton("OR");
        JButton biConditionalConnective = new JButton("IFF");
        JButton conditionalConnective = new JButton("IF");

        connectivesPanel.add(leftParentheses);
        connectivesPanel.add(rightParentheses);
        connectivesPanel.add(notConnective);
        connectivesPanel.add(andConnective);
        connectivesPanel.add(orConnective);
        connectivesPanel.add(conditionalConnective);
        connectivesPanel.add(biConditionalConnective);

        frame.setVisible(true);
    }

    private static void initMap() {
        connectiveMap.put(Connectives.PARENTHESES, Arrays.asList("(", ")"));
        connectiveMap.put(Connectives.TILDE, Arrays.asList("~"));
        connectiveMap.put(Connectives.AND, Arrays.asList("AND"));
        connectiveMap.put(Connectives.OR, Arrays.asList("OR"));
        connectiveMap.put(Connectives.CONDITIONAL, Arrays.asList("IF"));
        connectiveMap.put(Connectives.BICONDITIONAL, Arrays.asList("IFF"));

    }

    private static void deMorganSwitch(boolean var1, boolean var2, Connectives connectives) {

    }

}