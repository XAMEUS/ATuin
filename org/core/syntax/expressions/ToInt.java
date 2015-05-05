package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.syntax.Expression;

public class ToInt implements Expression {

	private Expression expression;
	
	public ToInt(Expression exp) {
		this.expression = exp;
	}
	
	@Override
	public Number eval() throws Exception {
		return new Number(this.expression.eval().intValue());
	}

}
