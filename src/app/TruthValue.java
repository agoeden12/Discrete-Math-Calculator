package app;

import java.util.List;

/**
 * TruthValue
 */
public class TruthValue {

    private String statement = "";
    private boolean truthValue = false;


    public TruthValue(List<String> statementList) {
        statementList.forEach((value) -> {
            value = (value.equals("AND")) ? "&&" : value;
            value = (value.equals("OR")) ? "||" : value;
            setStatement(getStatement() + value);
        });
    }

    public boolean getTruthValue() {
        return truthValue;
    }

    public void setTruthValue(boolean truthValue) {
        this.truthValue = truthValue;
    }

    public String getStatement() {
       return statement; 
    }

    public void setStatement(String statement) {
        this.statement = statement; 
    }
}