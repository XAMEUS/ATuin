package org.core.syntax.expressions;

import org.core.syntax.Expression;

public class Product extends Expression {
	
	private Expression left, right;
	
	public Product(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int eval() throws Exception {
		return this.left.eval() * this.right.eval();
	}

}
