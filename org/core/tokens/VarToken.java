package org.core.tokens;

import org.core.Sym;

public class VarToken extends Token {

	private String value;

	public VarToken(Sym s, String name) {
		super(s);
		this.value = name;
	}

	public String getValue() {
		return this.value;
	}

	public boolean isEqual(Token t) {
		return (t.symbol() == Sym.INT &&
				((VarToken) t).getValue().equals(this.value));
	}

	public String toString() {
		return "Symbol : " + this.symbol + " | Value : " + this.getValue();
	}
}
