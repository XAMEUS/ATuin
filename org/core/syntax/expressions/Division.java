package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.syntax.Expression;

public class Division implements Expression {

	private Expression left, right;

	public Division(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Number eval() throws Exception {
		return this.left.eval().divide(this.right.eval());
	}

}
