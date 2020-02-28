package app;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * CalculateStatement
 */
public class CalculateStatement {

    private HashMap<String, boolean[]> valueTable = new HashMap<>();
    private int numberOfVariables;

    public CalculateStatement(ArrayList<TruthValue> truthValues, int numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
        initializeHashMap(truthValues);
        valueTable.entrySet().forEach(entry -> {
            System.out.print(String.format("%-10s", entry.getKey()));
            for (boolean value : entry.getValue()) {
                int newValue = value ? 1 : 0;
                System.out.print(" | " + newValue);
            }
            System.out.println();
        });
    }

    private void initializeHashMap(ArrayList<TruthValue> truthValues) {
        if (numberOfVariables == 1) {
            // Table of P
            boolean[] pArray = new boolean[] { true, false };
            for (TruthValue value : truthValues) {
                if (value.getStatement().equals("p")) {
                    valueTable.put("p", pArray);
                } else {
                    generateHashMap(value.getStatement());
                }
            }
        } else if (numberOfVariables == 2) {
            // Table of P and Q
            boolean[] pArray = new boolean[] { true, true, false, false };
            boolean[] qArray = new boolean[] { true, false, true, false };
            for (TruthValue value : truthValues) {
                if (value.getStatement().equals("p")) {
                    valueTable.put("p", pArray);
                } else if (value.getStatement().equals("q")) {
                    valueTable.put("q", qArray);
                } else {
                    generateHashMap(value.getStatement());
                }
            }
        } else {
            // Table of P, Q, and R
            boolean[] pArray = new boolean[] { true, true, true, true, false, false, false, false };
            boolean[] qArray = new boolean[] { true, true, false, false, true, true, false, false };
            boolean[] rArray = new boolean[] { true, false, true, false, true, false, true, false };
            for (TruthValue value : truthValues) {
                if (value.getStatement().equals("p")) {
                    valueTable.put("p", pArray);
                } else if (value.getStatement().equals("q")) {
                    valueTable.put("q", qArray);
                } else if (value.getStatement().equals("r")) {
                    valueTable.put("r", rArray);
                } else {
                    generateHashMap(value.getStatement());
                }
            }
        }
    }

    private void generateHashMap(String statement) {
        System.out.println(statement);
        if (statement.indexOf("~") == 0 && (statement.indexOf("(") == 1 || statement.substring(2).isEmpty())) {
            valueTable.put(statement, not(statement));
        } else if (statement.indexOf("||") != -1 && statement.indexOf("||") <= 2) {
            boolean[] firstCondition = valueTable.get(statement.substring(0, statement.indexOf("||")));
            if (statement.indexOf("(") == statement.indexOf("||") + 2) {
                int closingIndex = statement.indexOf(")");
                boolean[] secondCondition = valueTable.get(statement.substring(statement.indexOf("||") + 3, closingIndex));
                valueTable.put(statement, or(firstCondition, secondCondition));
            } else {
                boolean[] secondCondition = valueTable.get(statement.substring(statement.indexOf("||") + 2));
                valueTable.put(statement, or(firstCondition, secondCondition));
            }
        } else if (statement.indexOf("&&") != -1 && statement.indexOf("&&") <= 2) {
            boolean[] firstCondition = valueTable.get(statement.substring(0, statement.indexOf("&&")));
            if (statement.indexOf("(") == statement.indexOf("&&") + 2) {
                int closingIndex = statement.indexOf(")");
                boolean[] secondCondition = valueTable.get(statement.substring(statement.indexOf("&&") + 3, closingIndex));
                valueTable.put(statement, and(firstCondition, secondCondition));
            } else {
                boolean[] secondCondition = valueTable.get(statement.substring(statement.indexOf("&&") + 2));
                valueTable.put(statement, and(firstCondition, secondCondition));
            }
        } else if (statement.indexOf("(") == 0) {
            valueTable.put(statement, parentheses(statement));
        }
    }

    private boolean[] not(String statement) {

        if (statement.contains("(") && statement.indexOf("(") == 1) {
            int closingIndex = statement.indexOf(")");
            generateHashMap(statement.substring(2, closingIndex));
            boolean[] oldStatement = valueTable.get(statement.substring(2, closingIndex));
            boolean[] newStatement = new boolean[oldStatement.length];
            for (int i = 0; i < newStatement.length; i++) {
                newStatement[i] = !(oldStatement[i]);
            }
            return newStatement;
        } else {
            boolean[] oldStatement = valueTable.get(statement.substring(1, 2));
            boolean[] newStatement = new boolean[oldStatement.length];
            for (int i = 0; i < newStatement.length; i++) {
                newStatement[i] = !(oldStatement[i]);
            }
            return newStatement;
        }

    }

    private boolean[] or(boolean[] firstCondition, boolean[] secondCondition) {
        boolean[] newStatement = new boolean[firstCondition.length];
        for (int i = 0; i < newStatement.length; i++) {
            newStatement[i] = (firstCondition[i]) || (secondCondition[i]);
        }
        return newStatement;
    }

    private boolean[] and(boolean[] firstCondition, boolean[] secondCondition) {
        boolean[] newStatement = new boolean[firstCondition.length];
        for (int i = 0; i < newStatement.length; i++) {
            newStatement[i] = (firstCondition[i]) && (secondCondition[i]);
        }
        return newStatement;
    }

    private boolean[] parentheses(String statement) {
        try {
            int closingIndex = statement.indexOf(")");
            boolean[] firstCondition;
            boolean[] secondCondition;

            firstCondition = valueTable.get(statement.substring(1, closingIndex));
            statement = statement.substring(closingIndex + 1);

            if (statement.contains("(") && statement.indexOf("(") == 1) {
                closingIndex = statement.indexOf(")");
                secondCondition = valueTable.get(statement.substring(1, closingIndex));
            } else {
                secondCondition = valueTable.get(statement.substring(2));
            }

            if (statement.indexOf("&&") == 0) {
                return and(firstCondition, secondCondition);
            } else if (statement.indexOf("||") == 0) {
                return or(firstCondition, secondCondition);
            } else {
                return new boolean[firstCondition.length];
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new boolean[2];

        }
    }
}