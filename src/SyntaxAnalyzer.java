import org.apache.commons.collections4.map.MultiKeyMap;
import tokens.NonTerminal;
import tokens.Terminal;
import tokens.Token;

import java.util.*;

/**
 * Created by Gamer on 11/25/2016.
 */
public class SyntaxAnalyzer {

    private MultiKeyMap transitionTable = new MultiKeyMap();
    Stack<Token> stack = new Stack<>();
    Queue<Token> inputQueue;

    SyntaxAnalyzer () {
        Rule r = Rule.createEndRule();
        Terminal inputEnd = new Terminal("$");
        Terminal stackEnd = new Terminal("#");
        transitionTable.put(stackEnd,inputEnd,r);
    }

    public void addTransition(Token stackToken, Token inputToken, Rule rule) {
        transitionTable.put(stackToken,inputToken,rule);
    }

    public void analyzeInput(List<Terminal> input) {

        inputQueue = new LinkedList<>(input);
        stack.push(new Terminal("#"));
        stack.push(new NonTerminal("htmldocument"));

        Token currentInputToken, currentStackToken;
        Rule currentRule;

        while(checkTermination()) {

            currentInputToken = inputQueue.peek();
            currentStackToken = stack.peek();
            currentRule = (Rule) transitionTable.get(stack.peek(),currentInputToken);

            System.out.println("Applying rule:");
            System.out.println(currentRule.toString());
            System.out.println("Stack top: "+ currentStackToken);
            System.out.println("Current input: "+ currentInputToken.getName());
            System.out.println();

            if(currentRule.isPop()) {
                stack.pop();
                inputQueue.poll();
            } else {
                stack.pop();
                pushMultiple(stack,currentRule.getRightSide());
            }

        }
    }

    void pushMultiple(Stack s, List<Token> tokens ) {
        ListIterator<Token> listIter = tokens.listIterator(tokens.size());
        while (listIter.hasPrevious()) {
            s.push(listIter.previous());
        }
    }

    boolean checkTermination() {
        Token currentInputToken = inputQueue.peek();
        Token stackToken = stack.peek();
        Rule currentRule = (Rule) transitionTable.get(stackToken,currentInputToken);

        if(currentRule == null) {
            System.out.println("Invalid input");
            printState();

            return false;
        }

        if(currentRule.isEnd()) {
            System.out.println("Input is valid");
            printState();
            return false;
        }

        return true;
    }

    public void printState(){
        System.out.println("Stack contents:");
        for(Token t: stack) {
            System.out.println("\t" + t);
        }
        System.out.println("Input left:");
        for(Token t: inputQueue) {
            System.out.println("\t" + t.getName());
        }
    }
}
