package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Rule;
import tokens.NonTerminal;
import tokens.Terminal;
import tokens.Token;

public class CSVRuleReader {
// com.csvreader.CsvReader;
	//private List<RuleStructure> rulesDetails = new ArrayList<RuleStructure>();
	private HashMap<String, Rule> rulesMap;
	
	public CSVRuleReader(String filename) {
		rulesMap = new HashMap<String, Rule>();
		String line = "";
		
	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

	    	while ((line = br.readLine()) != null) {
	    		RuleStructure rs = processCsvLine(line);
	    		
	    		if (rs == null) {
	    			continue;
	    		} else {
	    			Rule rule = new Rule(new NonTerminal(rs.ruleName), rs.tokens);
	    			rulesMap.put(rs.id, rule);
	    		}
	        }

	     } catch (IOException e) {
	            e.printStackTrace();
	     }
	}
	
	public HashMap<String, Rule> getRulesDetails() {
		return this.rulesMap;
	}
	
	private RuleStructure processCsvLine(String csvLine) {
		String cvsSplitBy = ",";
		
		// use comma as separator
		String[] text = csvLine.split(cvsSplitBy);
		
		if (text.length == 0) {
			// empty line data
			return null;
		}
		
		if (text[0].equals("")) {
			// no valid rule, rule not defined with valid ID 
			return null;
		}
		
		return new RuleStructure(text[0], text[1], getTokensForRule(text[3]));
	}
	
	private List<Token> getTokensForRule(String ruleText) {
		List<Token> tokens = new ArrayList<Token>();
		
		// check whether some terminals are in the rule => then "" have to be checked and removed at the start and end of the line
		String rule = ruleText.replaceAll("^\"|\"$", "");
		
		// split rule by space 
		String[] splited = rule.split(" ");
		
		if (splited.length == 0) {
			// just one token in rule
			tokens.add(determineTokenType(rule));
		} else {
			// more tokens in rule separated by space
			for (String tokenStr : splited) {
				tokens.add(determineTokenType(tokenStr));
			}
		}
		
		return tokens;
	}
	
	private Token determineTokenType(String tokenStr) {
		String termPattern = "\\\"(.*)\\\"";
		
		// Create a Pattern object
	    Pattern r = Pattern.compile(termPattern);

	    // Now create matcher object.
	    Matcher m = r.matcher(tokenStr);
	      
	    if (m.find( )) {
	    	// Quotes duplication, just delete the quote at the beginning and on the end of the terminal name
	    	String termStr = tokenStr.replaceAll("\"", "");
	        // String was found as a terminal
	    	return new Terminal(termStr); 
	    } else {
	    	return new NonTerminal(tokenStr);
	    }
	}	
}
