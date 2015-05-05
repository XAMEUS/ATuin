package org.core.syntax.instructions;

import org.core.env.Values;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class While implements Instruction {
	
	private Expression expression;
	private Instruction instruction;
	
	public While(Expression exp, Instruction instr) {
		this.expression = exp;
		this.instruction = instr;
	}

	@Override
	public void exec() throws Exception {
		while (!this.expression.eval().equals(Values.FALSE.getValue())) {
			this.instruction.exec();
		}
	}
	
}
