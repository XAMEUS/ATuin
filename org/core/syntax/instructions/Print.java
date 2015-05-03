package org.core.syntax.instructions;

import org.core.env.Number;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.ui.controller.Controller;

public class Print implements Instruction {
	
	private Expression expression;
	
	public Print(Expression exp) {
		this.expression = exp;
	}
	
	@Override
	public void exec() throws Exception {
		Number n = expression.eval();
		System.out.println(n);
		Controller.out.println(""+n);
	}

}
