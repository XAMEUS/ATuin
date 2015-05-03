package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.syntax.Expression;

public class Int implements Expression {
	
	private Number value;
	
	public Int(Number value) {
		this.value = value;
	}
	
	@Override
	public Number eval() throws Exception {
		return this.value;
	}

}
