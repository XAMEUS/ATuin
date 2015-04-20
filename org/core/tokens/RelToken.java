package org.core.tokens;

class RelToken extends Token {
	
	private char value;
	
	public RelToken(Sym s, char rel) {
		super(s);
		this.value = rel;
	}

	public int getValue() {
		return this.value;
	}

	public boolean isEqual(Token t) {
		return (t.symbol() == Sym.REL && ((RelToken) t).getValue() == this.value);
	}

	public String toString() {
		return "Symbol : " + this.symbol + " | Value : " + this.value;
	}
	
}
