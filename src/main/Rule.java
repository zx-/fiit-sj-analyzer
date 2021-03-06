package main;
import tokens.NonTerminal;
import tokens.Token;

import java.util.List;

/**
 * Created by Gamer on 11/25/2016.
 */
public class Rule {

    private final NonTerminal leftSide;
    private final List<Token> rightSide;

    private boolean isEnd = false;
    private boolean isPop = false;

    public Rule (NonTerminal leftSide, List<Token> rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public static Rule createEndRule(){
        Rule r = new Rule(null,null);
        r.isEnd = true;
        return r;
    }
    public static Rule createPopRule(){
        Rule r = new Rule(null,null);
        r.isPop = true;
        return r;
    }

    public String toString() {
        if(isPop()) return "STACK POP Z";
        if(isEnd()) return "END";

        StringBuilder b = new StringBuilder();

        b.append(leftSide.getName());
        b.append(" -> ");
        for(Token t:rightSide) {
            b.append(t.getName());
            b.append(" ");
        }
        if(rightSide.size() == 0)
            b.append("eps");

        return b.toString();
    }

    public boolean isPop(){ return isPop; }

    public boolean isEnd(){ return isEnd; }

    public List<Token> getRightSide() {
        return rightSide;
    }

    public NonTerminal getLeftSide() {
        return leftSide;
    }
}
