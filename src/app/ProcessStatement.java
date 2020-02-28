package app;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * CalculateStatement
 */
public class ProcessStatement {

    private ArrayList<TruthValue> truthValues = new ArrayList<TruthValue>();

    public ProcessStatement(String statement, int numberOfVariables) {
        System.out.println("statement: (Process) " + statement);
        System.out.println("numberOfVariables: (Process) " + numberOfVariables);

        List<String> statementList = Arrays.asList(statement.substring(1).split(" "));

        processStatement(statementList);

        addBaseVariables(numberOfVariables);

        Collections.reverse(truthValues);
        System.out.println("---------------------------");
        truthValues.forEach((value) -> {
            System.out.println(value.getStatement());
        });
    }

    private void addBaseVariables(int numberOfVariables) {
        if (numberOfVariables > 1) {
            if (numberOfVariables > 2)
                saveTruthStatement(Arrays.asList("r"));
            saveTruthStatement(Arrays.asList("q"));
        }
        saveTruthStatement(Arrays.asList("p"));
    }

    private void processStatement(List<String> statementList) {

        System.out.println("Processing Statement: " + statementList);

        while (!statementList.isEmpty()) {

            switch (statementList.get(0)) {
                case "(":
                    statementList = processParentheses(statementList);
                    break;

                case "~":
                    statementList = processNot(statementList);
                    break;

                case "p":
                    statementList = processVariable(statementList);
                    break;

                case "q":
                    statementList = processVariable(statementList);
                    break;

                default:
                    return;
            }
        }
    }

    private List<String> processParentheses(List<String> statementList) {
        int closingIndex = statementList.indexOf(")");
        int statementListSize = statementList.size();

        try {
            if (statementList.get(closingIndex + 1).equals("AND") || statementList.get(closingIndex + 1).equals("OR")) {
                saveTruthStatement(statementList);
                processStatement(statementList.subList(1, closingIndex));
                return statementList.subList(closingIndex + 2, statementListSize);
            } else {
                return statementList.subList(closingIndex + 1, statementListSize);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            processStatement(statementList.subList(1, statementList.indexOf(")")));
            return statementList.subList(closingIndex + 1, statementListSize);
        } catch (IndexOutOfBoundsException e) {
            processStatement(statementList.subList(1, statementList.indexOf(")")));
            return statementList.subList(closingIndex + 1, statementListSize);
        }
    }

    private List<String> processNot(List<String> statementList) {
        try {
            if (statementList.get(1).equals("(")) {
                int closingIndex = statementList.indexOf(")");
                saveTruthStatement(statementList.subList(0, closingIndex + 1));
                processStatement(statementList.subList(1, closingIndex + 1));
                return statementList.subList(closingIndex + 1, statementList.size());
            } else if (statementList.get(2).equals("AND") || statementList.get(2).equals("OR")) {
                saveTruthStatement(statementList);
                saveTruthStatement(statementList.subList(0, 2));
                return statementList.subList(3, statementList.size());
            } else {
                saveTruthStatement(statementList.subList(0, 2));
                return statementList.subList(2, statementList.size());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            saveTruthStatement(statementList.subList(0, 2));
            return statementList.subList(2, statementList.size());
        } catch (IndexOutOfBoundsException e) {
            saveTruthStatement(statementList.subList(0, 2));
            return statementList.subList(2, statementList.size());
        }
    }

    private List<String> processVariable(List<String> statementList) {
        try {
            if (statementList.get(1).equals("AND") || statementList.get(1).equals("OR")) {
                saveTruthStatement(statementList);
                return statementList.subList(2, statementList.size());
            } else {
                return statementList.subList(1, statementList.size());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return statementList.subList(1, statementList.size());
        } catch (IndexOutOfBoundsException e) {
            return statementList.subList(1, statementList.size());
        }
    }

    private void saveTruthStatement(List<String> statementList) {
        truthValues.add(new TruthValue(statementList));
    }
}