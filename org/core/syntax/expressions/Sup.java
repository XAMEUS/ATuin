package org.core.syntax.expressions;

import org.core.syntax.Expression;

public class Sup extends Expression {
	
	private Expression left, right;
	private boolean strict;
	
	public Sup(Expression left, Expression right, boolean strict) {
		this.left = left;
		this.right = right;
		this.strict = strict;
	}
	
	@Override
	public int eval() throws Exception {
		if (this.left.eval() < this.right.eval())
			return 0;
		if (this.strict && this.left.eval() == this.right.eval())
			return 0;
		return 1;
	}

}
