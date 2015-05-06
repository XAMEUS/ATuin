package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.ui.controller.TurtleBrain;

public class Turn implements Instruction {
	
	private Expression exp;
	
	public Turn(Expression exp) {
		this.exp = exp;
	}
	
	@Override
	public void exec() throws Exception {
		TurtleBrain.turn(exp.eval());
	}

}
