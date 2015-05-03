package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.env.Values;
import org.core.syntax.Expression;

public class And implements Expression {

	private Expression left, right;
	
	public And(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Number eval() throws Exception {
		if (!this.left.eval().equals(Values.FALSE.getValue()) &&
				!this.right.eval().equals(Values.FALSE.getValue()))
			return Values.TRUE.getValue();
		return Values.FALSE.getValue();
	}

}
