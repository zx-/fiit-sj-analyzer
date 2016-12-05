package main;
import java.util.ArrayList;
import java.util.List;


import csv.CSVStackReader;
import tokens.NonTerminal;
import tokens.Terminal;
import tokens.Token;

public class Main {

    public static void main(String[] args) {

//    	List<Terminal> allowedTerminals = new ArrayList<>();
//    	allowedTerminals.add(new Terminal("<html>"));
//    	allowedTerminals.add(new Terminal("</html>"));
//		allowedTerminals.add(new Terminal("<head>"));
//		allowedTerminals.add(new Terminal("</head>"));
//		allowedTerminals.add(new Terminal("<body>"));
//		allowedTerminals.add(new Terminal("</body>"));
//
//    	LexicalAnalyzer la = new LexicalAnalyzer(allowedTerminals);
//    	List<Terminal> inputTokens = la.getTerminalsFromString(" <html><head></head> <body> </body> </html> ");

    	
    	SyntaxAnalyzer sa = new SyntaxAnalyzer();
    	
    	CSVStackReader stackReader = new CSVStackReader("zasobnik.csv", sa);
    	List<Terminal> allowedTerminals = stackReader.getTerminals();
    	// delete the first empty column value
    	allowedTerminals.remove(0);
    	
    	LexicalAnalyzer la = new LexicalAnalyzer(allowedTerminals);
    	List<Terminal> inputTokens = la.getTerminalsFromString(" <html><head></head> <body> </body> </html> ");
    	
    	sa.analyzeInput(inputTokens);   
    }
}
