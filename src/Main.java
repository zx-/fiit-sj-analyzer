import tokens.NonTerminal;
import tokens.Terminal;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SyntaxAnalyzer a = new SyntaxAnalyzer();

        List<Token> l = new ArrayList<>();
        l.add(new Terminal("<html>"));
        l.add(new Terminal("</html>"));
        Rule r = new Rule(new NonTerminal("htmldocument"),l);

        a.addTransition(new NonTerminal("htmldocument"),new Terminal("<html>"), r);
        a.addTransition(new Terminal("<html>"),new Terminal("<html>"), Rule.createPopRule());
        a.addTransition(new Terminal("</html>"),new Terminal("</html>"), Rule.createPopRule());

        List<Terminal> allowedTerminals = new ArrayList<>();
        allowedTerminals.add(new Terminal("<html>"));
        allowedTerminals.add(new Terminal("</html>"));

        LexicalAnalyzer la = new LexicalAnalyzer(allowedTerminals);
        List<Terminal> inputTokens = la.getTerminalsFromString(" <html> </html>   ");

        a.analyzeInput(inputTokens);
    }
}
