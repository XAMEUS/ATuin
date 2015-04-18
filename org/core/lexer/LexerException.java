package org.core.lexer;

public class LexerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LexerException(int line, int column) {
		super("Unexpected character at line " + line + " column " + column + ".");
	}
}