package csv;

import java.util.List;

import tokens.Token;

public class RuleStructure {
	public String id;
	public String ruleName;
	public List<Token> tokens;
	
	public RuleStructure(String id, String ruleName, List<Token> tokens) {
		this.id = id;
		this.ruleName = ruleName;
		this.tokens = tokens;
		//this.first = first;
		//this.follow = follow;
	}
	
}
