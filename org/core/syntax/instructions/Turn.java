package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class Turn extends Instruction {
	
	private Expression exp;
	
	public Turn(Expression exp) {
		this.exp = exp;
	}
	
	@Override
	public void exec() throws Exception {
		// TODO Auto-generated method stub
	}

}
