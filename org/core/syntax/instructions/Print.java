package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class Print extends Instruction {
	
	private Expression expression;
	
	public Print(Expression exp) {
		this.expression = exp;
	}
	
	@Override
	public void exec() throws Exception {
		System.out.println(expression.eval());
	}

}
