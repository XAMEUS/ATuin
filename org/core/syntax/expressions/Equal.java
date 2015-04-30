package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.env.Values;
import org.core.syntax.Expression;

public class Equal extends Expression {

	private Expression left, right;
	
	public Equal(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Number eval() throws Exception {
		if (!this.left.eval().equals(this.right.eval()))
			return Values.FALSE.getValue();
		return Values.TRUE.getValue();
	}

}
