package org.core.syntax.expressions;

import org.core.syntax.Expression;

public class Equal extends Expression {

	private Expression left, right;
	
	public Equal(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int eval() throws Exception {
		if (this.left.eval() == this.right.eval())
			return 1;
		return 0;
	}

}
