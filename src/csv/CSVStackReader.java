package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.Rule;
import main.SyntaxAnalyzer;
import tokens.NonTerminal;
import tokens.Terminal;
import tokens.Token;
import util.StringUtil;

public class CSVStackReader {
	private List<Terminal> terminals;
	private HashMap<String, Rule> rulesMap;
	
	public CSVStackReader(String filename, SyntaxAnalyzer sa) {
		rulesMap = new CSVRuleReader("gramatika.csv").getRulesDetails();
			
		terminals = new ArrayList<Terminal>();
		String line = "";
		String cvsSplitBy = ",";
		int row = 0;
		
	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

	    	while ((line = br.readLine()) != null) {	    		
	    		// use comma as separator
	    		String[] text = line.split(cvsSplitBy);
    			int column = 0;
	    		
	    		for (String tmpStr: text) {
	    			// Numeric value, add new transition
	    			if (tmpStr.matches("^\\d+$")) {	    					
	    				Token stackToken = new NonTerminal(text[0]);
	    				Token inputToken = terminals.get(column);
	    					
	    				sa.addTransition(stackToken, inputToken, rulesMap.get(tmpStr));
	    					
	    			} else {
	    				if (row == 0) {
	    					// get a list of terminals from the first line (header)
	    					tmpStr = StringUtil.deleteQuotes(tmpStr, 3);
	    					terminals.add(new Terminal(tmpStr));
	    				}
	    					
	    				if (tmpStr.equals("Z")) {
	    					Token stackToken = new Terminal(StringUtil.deleteQuotes(text[0], 3));
		    				Token inputToken = terminals.get(column);
		    				sa.addTransition(stackToken, inputToken, Rule.createPopRule());
	    				}
	    			}
	    				
		    		column++;
	    		}
	    		
	    		row++;
	        }

	     } catch (IOException e) {
	            e.printStackTrace();
	     }
	}
	
	public List<Terminal> getTerminals() {
		return this.terminals;
	}

}
