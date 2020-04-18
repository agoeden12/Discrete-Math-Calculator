package app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * CalculateStatement
 */
public class CalculateStatement {

    private HashMap<String, boolean[]> valueTable = new HashMap<>();
    private int numberOfVariables;

    public CalculateStatement(ArrayList<TruthValue> truthValues, int numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
        int longestString = truthValues.get(truthValues.size() - 1).getStatement().length();

        System.out.println("\n");
        initializeHashMap(truthValues);


        Map<String, boolean[]> sort = new TreeMap<String, boolean[]>(
            new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    if (s2.length() > s1.length()) {
                        return -1;
                    } else if (s2.length() < s1.length()) {
                        return 1;
                    } else {
                        return s2.compareTo(s1);
                    }
                }
        });

        sort.putAll(valueTable);
        
        
        System.out.println("\n");
        sort.entrySet().forEach(entry -> {
            System.out.print(String.format("%-" + (longestString + 4) + "s", entry.getKey()));
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
                    generateHashMapNew(value.getStatement());
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
                    generateHashMapNew(value.getStatement());
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
                    generateHashMapNew(value.getStatement());
                }
            }
        }
    }

    private void generateHashMapNew(String statement) {
        System.out.println(statement);
        boolean[] firstCondition;
        boolean[] secondCondition;
        if (statement.indexOf("(") == 0) {
            valueTable.put(statement, parentheses(statement));
        } else if (statement.indexOf("|") == 1 || statement.indexOf("|") == 2) {
            int index = statement.indexOf("||");
            firstCondition = valueTable.get(statement.substring(0, index));

            String postOr = statement.substring(index+2);
            secondCondition = postOr.indexOf("(") == 0 ? valueTable.get(postOr.substring(1, getFinalParenthesesIndex(postOr))) : valueTable.get(postOr);

            valueTable.put(statement, or(firstCondition, secondCondition));
        } else if (statement.indexOf("&") == 1 || statement.indexOf("&") == 2) {
            int index = statement.indexOf("&&");
            firstCondition = valueTable.get(statement.substring(0, index));

            String postAnd = statement.substring(index+2);
            secondCondition = postAnd.indexOf("(") == 0 ? valueTable.get(postAnd.substring(1, getFinalParenthesesIndex(postAnd))) : valueTable.get(postAnd);

            valueTable.put(statement, and(firstCondition, secondCondition));
        } else if (statement.indexOf("~") == 0) {

            if (statement.contains("(") && statement.indexOf("(") == 1) {
                int closingIndex = getFinalParenthesesIndex(statement.substring(1)) + 1;
                if (valueTable.containsKey(statement.substring(0, closingIndex + 1))){
                    firstCondition = valueTable.get(statement.substring(0, closingIndex + 1));
                    
                    String post = statement.substring(closingIndex + 3);
                    secondCondition = post.indexOf("(") == 0 ? valueTable.get(post.substring(1, getFinalParenthesesIndex(post))) : valueTable.get(post);
                    
                    valueTable.put(statement, statement.charAt(closingIndex + 1) == '|' ? or(firstCondition, secondCondition) : and(firstCondition, secondCondition));
                } else {
                    valueTable.put(statement, not(statement));
                }
            } else {
                valueTable.put(statement, not(statement));
            }
        } 
    }

    private boolean[] not(String statement) {

        if (statement.contains("(") && statement.indexOf("(") == 1) {
            int closingIndex = getFinalParenthesesIndex(statement.substring(1)) + 1; // since the statement is a not
                                                                                     // statement it doesn't account for
                                                                                     // index
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

            // System.out.println(String.format("%b = %b || %b", newStatement[i], firstCondition[i], secondCondition[i]));

        }
        return newStatement;
    }

    private boolean[] and(boolean[] firstCondition, boolean[] secondCondition) {
        boolean[] newStatement = new boolean[firstCondition.length];
        for (int i = 0; i < newStatement.length; i++) {
            newStatement[i] = (firstCondition[i]) && (secondCondition[i]);

            // System.out.println(String.format("%b = %b && %b", newStatement[i], firstCondition[i], secondCondition[i]));
        }
        return newStatement;
    }

    private boolean[] parentheses(String statement) {
        try {
            int closingIndex = getFinalParenthesesIndex(statement);
            int startingIndex = statement.indexOf("(") + 1;
            boolean[] firstCondition;
            boolean[] secondCondition;

            // System.out.println("Line 166 ----");
            firstCondition = valueTable.get(statement.substring(1, closingIndex));
            statement = statement.substring(closingIndex + 1);
            
            String operand = statement.substring(0, 2);
            statement = statement.substring(2);

            if (statement.contains("(") && statement.indexOf("(") == 0) {
                startingIndex = statement.indexOf("(") + 1;
                closingIndex = getFinalParenthesesIndex(statement);

                secondCondition = valueTable.get(statement.substring(startingIndex, closingIndex));
            } else {
                secondCondition = valueTable.get(statement);
            }

            if (operand.equals("&&")) {
                return and(firstCondition, secondCondition);
            } else if (operand.equals("||")) {
                return or(firstCondition, secondCondition);
            } else {
                return new boolean[firstCondition.length];
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new boolean[2];
        }
    }

    private int getFinalParenthesesIndex(String statement) {
        int index = -1;
        int parenthesesCount = 0;

        for (int i = 0; i < statement.length(); i++) {
            parenthesesCount += statement.substring(i, i + 1).equals("(") ? 1 : 0;
            parenthesesCount += statement.substring(i, i + 1).equals(")") ? -1 : 0;
            index++;
            if (parenthesesCount == 0)
                break;
        }

        return index;
    }
}