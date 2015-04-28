package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class Forward extends Instruction {
	
	private Expression exp;
	
	public Forward(Expression exp) {
		this.exp = exp;
	}
	
	@Override
	public void exec() throws Exception {
		// TODO Auto-generated method stub
	}

}
