package org.core.syntax.expressions;

import org.core.env.Number;
import org.core.env.Values;
import org.core.syntax.Expression;

public class Inf extends Expression {

	private Expression left, right;
	private boolean strict;
	
	public Inf(Expression left, Expression right, boolean strict) {
		this.left = left;
		this.right = right;
		this.strict = strict;
	}
	
	@Override
	public Number eval() throws Exception {
		if (this.left.eval().superior(this.right.eval()))
			return Values.FALSE.getValue();
		if (this.strict && this.left.eval().equals(this.right.eval()))
			return Values.FALSE.getValue();
		return Values.TRUE.getValue();
	}

}
