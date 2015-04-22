package org.core.parser;

import org.core.lexer.Lexer;
import org.core.tokens.IntToken;
import org.core.tokens.Sym;
import org.core.tokens.Token;
import org.core.tokens.VarToken;

public class LookAhead1 {

	/**
	 * Simulating a reader class for a stream of Token
	 */

	private Token current;
	private Lexer lexer;

	public LookAhead1(Lexer l) throws Exception {
		lexer = l;
		current = lexer.yylex();
	}
	
	/**
	 * Check whether the first character is of the given type.
	 * @param s {@link Sym}, the reference type.
	 * @return boolean true if it is the correct type.
	 * @throws Exception
	 */
	public boolean check(Sym s) throws Exception {
		return (current.symbol() == s);
	}
	
	/**
	 * Consume a token of the given type s from the stream, throws exception when the contents
	 * does not start on s.
	 * @param s {@link Sym}, the token to consume.
	 * @throws Exception, when the contents does not start with the given type.
	 */
	public void eat(Sym s) throws Exception {
		if (!check(s)) {
			throw new Exception(this.getPosition() +
					"\nCan't eat " + s + " current being " + current);
		}
		
		// debug print
		System.out.println(current);

		current = lexer.yylex();
	}
	
	/**
	 * Gives the value of the IntToken, or it rises an exception if not IntToken.
	 * @return int
	 * @throws Exception
	 */
	public int getIntValue() throws Exception {
		if (current instanceof IntToken) {
			IntToken t = (IntToken) current;
			return t.getValue();
		}
		else {
			throw new Exception(this.getPosition() +
					"\nLookAhead error: get value from a non-valued token");
		}
	}

	/**
	 * Gives the value of the VarToken, or it rises an exception if not VarToken.
	 * @return String
	 * @throws Exception
	 */
	public String getStringValue() throws Exception {
		if (current instanceof VarToken) {
			VarToken t = (VarToken) current;
			return t.getValue();
		}
		else {
			throw new Exception(this.getPosition() +
					"\nLookAhead error: get value from a non-string token");
		}
	}
	
	/**
	 * For debug
	 * @return {@link Token}, the current token.
	 */
	public Token getCurrent() {
		return current;
	}

	public String getString() {
		return current.toString();
	}

	public String getPosition() {
		int[] pos = lexer.getPosition();
		return "Reading at line " + pos[0] + ", column " + pos[1];
	}

}
