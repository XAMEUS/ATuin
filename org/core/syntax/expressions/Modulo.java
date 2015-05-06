package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.syntax.Expression;

public class Modulo implements Expression {

	private Expression left, right;

	public Modulo(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Number eval() throws Exception {
		return this.left.eval().modulo(this.right.eval());
	}
}