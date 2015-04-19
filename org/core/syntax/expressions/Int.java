package org.core.syntax.expressions;

import org.core.syntax.Expression;

public class Int extends Expression {
	
	private int value;
	
	public Int(int value) {
		this.value = value;
	}
	
	@Override
	public int eval() throws Exception {
		return this.value;
	}

}
