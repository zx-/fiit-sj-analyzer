package main;
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
    Queue<Terminal> inputQueue;
    boolean wasError = false;

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

        printTokens(input);

        inputQueue = new LinkedList<>(input);
        stack.push(new Terminal("#"));
        stack.push(new NonTerminal("htmldocument"));

        Token currentInputToken, currentStackToken;
        Rule currentRule;

        while(checkTermination()) {

            currentInputToken = inputQueue.peek();
            currentStackToken = stack.peek();
            currentRule = (Rule) transitionTable.get(stack.peek(),currentInputToken);

            System.out.println("Input left:");
            for(Terminal t:inputQueue){
                System.out.print(" " + t.getName() + (t.isText()?"("+t.getText()+")":""));
            }
            System.out.println();
            printRule(currentRule,currentStackToken,currentInputToken);

            if(currentRule.isPop()) {
                stack.pop();
                inputQueue.poll();
            } else {
                stack.pop();
                pushMultiple(stack,currentRule.getRightSide());
            }

        }
    }

    private void printTokens(List<Terminal> input) {
        System.out.println("Analyzing tokens:");

        for(Terminal t:input){
            System.out.print(" " + t.getName());
        }
        System.out.println();
        System.out.println();
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
            System.out.println("Invalid input \nno suitable rule found \n");

            wasError = true;

            if(recover()){
                return true;
            } else {
                printState();
                return false;
            }
        }

        if(currentRule.isEnd()) {
            printRule(currentRule,stackToken,currentInputToken);

            if (wasError) {
                System.out.println("Invalid input\n");
            } else {
                System.out.println("Input is valid \n");
            }
            return false;
        }

        return true;
    }

    public boolean recover () {
        Token currentInputToken = inputQueue.peek();
        Token stackToken = stack.peek();
        Rule currentRule = (Rule) transitionTable.get(stackToken,currentInputToken);
        Terminal end = new Terminal("$");

        int skipped = 0;
        while( currentRule == null && !currentInputToken.equals(end)) {
            inputQueue.poll();
            currentInputToken = inputQueue.peek();
            currentRule = (Rule) transitionTable.get(stackToken,currentInputToken);
            skipped++;
        }

        if(currentInputToken.equals(end)) {
            System.out.println("Could not recover");
            return false;
        } else {
            System.out.println(String.format("Skipped %d tokens and recoverd",skipped));
            return true;
        }
    }

    public void printRule(Rule rule, Token stackToken, Token inputToken){
        System.out.println("Applying rule:");
        System.out.println("\t" + rule.toString());
        System.out.println("Stack top: \n\t"+ stackToken);
        System.out.print("Current input: \n\t"+ inputToken.getName());
        if(((Terminal) inputToken).isText()) {
            System.out.println(" - " + ((Terminal) inputToken).getText());
        } else {
            System.out.println();
        }
        System.out.println();
    }

    public void printState(){
        System.out.println("Stack contents:");
        while(!stack.empty()) {
            System.out.println("\t" + stack.pop());
        }
        System.out.println("Input left:");
        for(Token t: inputQueue) {
            System.out.print("\t" + t.getName());
            if(((Terminal) t).isText()) {
                System.out.println(" - " + ((Terminal) t).getText());
            } else {
                System.out.println();
            }
        }
    }
}
