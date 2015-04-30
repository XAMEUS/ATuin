package org.core.syntax.expressions;

import org.core.syntax.Expression;

public class Xor extends Expression {
	
	private Expression left, right;
	
	public Xor(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int eval() throws Exception {
		if (this.left.eval() == 1 ^ this.right.eval() == 1)
			return 1;
		return 0;
	}

}
