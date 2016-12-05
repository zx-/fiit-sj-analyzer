package main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import csv.CSVStackReader;
import tokens.NonTerminal;
import tokens.Terminal;
import tokens.Token;
import util.FileHandlerUtil;

public class Main {
	public static final String INPUT_1 = "input/input1.txt";
	public static final String INPUT_2 = "input/input2.txt";
	
    public static void main(String[] args) {    	
    	SyntaxAnalyzer sa = new SyntaxAnalyzer();
    	
    	CSVStackReader stackReader = new CSVStackReader("zasobnik.csv", sa);
    	List<Terminal> allowedTerminals = stackReader.getTerminals();
    	// delete the first empty column value
    	allowedTerminals.remove(0);
    	
    	LexicalAnalyzer la = new LexicalAnalyzer(allowedTerminals);
    	//List<Terminal> inputTokens = la.getTerminalsFromString(" <html> <head> <meta name= \"par_name\" content= \"par_cont\" > </head> <body> </body> </html> ");
    	
    	List<Terminal> inputTokens = null;
		try {
			inputTokens = la.getTerminalsFromString(FileHandlerUtil.loadFile(INPUT_2));
		} catch (IOException e) {
			e.printStackTrace();
		}
    			
    	sa.analyzeInput(inputTokens);   
    }
}
