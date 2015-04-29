package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.ui.controller.Controller;

public class Print extends Instruction {
	
	private Expression expression;
	
	public Print(Expression exp) {
		this.expression = exp;
	}
	
	@Override
	public void exec() throws Exception {
		int v = expression.eval();
		System.out.println(v);
		Controller.out.println(""+v);
	}

}
