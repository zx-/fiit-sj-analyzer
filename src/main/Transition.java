package main;
import tokens.Token;

public class Transition {
	private Token stackToken;
	private Token inputToken;
	private int ruleId;
	
	public Transition(Token stackToken, Token inputToken, int ruleId) {
		this.stackToken = stackToken;
		this.inputToken = inputToken;
		this.ruleId = ruleId;
	}
	
	public Token getStackToken() {
		return this.stackToken;
	}
	
	public Token getInputToken() {
		return this.inputToken;
	}
	
	public int getRuleId() {
		return this.ruleId;
	}
}
