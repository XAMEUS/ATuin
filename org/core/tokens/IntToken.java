package org.core.tokens;

import org.core.Sym;

public class IntToken extends Token {

	private int value;

	public IntToken(Sym s, int n) {
		super(s);
		this.value = n;
	}

	public int getValue() {
		return this.value;
	}

	public boolean isEqual(Token t) {
		return (t.symbol() == Sym.INT && ((IntToken) t).getValue() == this.value);
	}

	public String toString() {
		return "Symbol : " + this.symbol + " | Value : " + this.getValue();
	}
}