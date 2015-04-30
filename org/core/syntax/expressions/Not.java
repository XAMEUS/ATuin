package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.env.Values;
import org.core.syntax.Expression;

public class Not extends Expression {
	
	private Expression expression;
	
	public Not(Expression exp) {
		this.expression = exp;
	}
	
	@Override
	public Number eval() throws Exception {
		if (expression.eval().equals(Values.FALSE.getValue()))
			return Values.TRUE.getValue();
		return Values.FALSE.getValue();
	}

}
