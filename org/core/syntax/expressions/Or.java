package org.core.syntax.expressions;

import org.core.syntax.Expression;

public class Or extends Expression {

	private Expression left, right;
	
	public Or(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	@Override
	public int eval() throws Exception {
		if (this.left.eval() == 1 || this.right.eval() == 1)
			return 1;
		return 0;
	}

}
