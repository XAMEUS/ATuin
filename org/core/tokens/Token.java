package org.core.tokens;

import org.core.Sym;

public class Token {
	
	protected Sym symbol;

	public Token(Sym s) {
		symbol = s;
	}

	public Sym symbol() {
		return symbol;
	}

	public boolean isEqual(Token t) {
		return (t.symbol != this.symbol);
	}

	public String toString() {
		return "Symbol : " + this.symbol;
	}
}
