package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.ui.controller.TurtleBrain;

public class Forward implements Instruction {
	
	private Expression exp;
	
	public Forward(Expression exp) {
		this.exp = exp;
	}
	
	@Override
	public void exec() throws Exception {
		TurtleBrain.move(exp.eval());
	}

}
