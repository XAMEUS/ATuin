package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.syntax.Expression;

public class Difference implements Expression {
	
	private Expression left, right;
	
	public Difference(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Number eval() throws Exception {
		return this.left.eval().subtract(this.right.eval());
	}

}
