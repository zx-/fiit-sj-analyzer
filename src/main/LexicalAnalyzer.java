package main;
import tokens.Terminal;

import java.util.*;

/**
 * Created by Gamer on 11/25/2016.
 */
public class LexicalAnalyzer {

    Set<Terminal> terminals = new HashSet<>();

    LexicalAnalyzer (List<Terminal> validTerminals) {
        for(Terminal t:validTerminals)
            terminals.add(t);
    }

    public List<Terminal> getTerminalsFromString(String input){
        List<String> inputList = splitInput(input);
        LinkedList<Terminal> terminalList = new LinkedList<>();
        Terminal candidate;

        for(String s:inputList) {
            candidate = new Terminal(s);

            if(terminals.contains(candidate)) {
                terminalList.add(candidate);
            }
            else {
                if(terminalList.peekLast() == null || !terminalList.getLast().isText() ) {
                    candidate.appendText(s);
                    candidate.setName("TEXT");
                    terminalList.add(candidate);
                }
                else {
                    terminalList.getLast().appendText(s);
                }
            }
        }

        terminalList.add(new Terminal("$"));
        terminalList.removeIf(terminal -> {
            if(!terminal.isText()) return false;

            for(char c :terminal.getText().toCharArray()){
                if(!Character.isWhitespace(c))
                    return false;
            }

            return true;
        });

        return terminalList;
    }

    private List<String> splitInput(String s){
        List<String> l = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        boolean wasLeft = false;

        for(int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);

            if(c == '<') {
                wasLeft = true;

                if(b.length() != 0){
                    l.add(b.toString());
                    b = new StringBuilder();
                }
            }

            b.append(c);

            if(c == '>' && wasLeft) {
                wasLeft = false;
                l.add(b.toString());
                b = new StringBuilder();
            }
        }

        if(b.length() != 0){
            l.add(b.toString());
        }

        return l;
    }

}
