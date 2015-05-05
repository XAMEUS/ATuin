package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class For implements Instruction {
	
	private Expression exp;
	private Instruction instr;
	
	public For(Expression exp, Instruction instr) {
		this.exp = exp;
		this.instr = instr;
	}
	
	@Override
	public void exec() throws Exception {
		for (int i = 0; i < exp.eval().intValue(); i++) {
			if (instr != null)
				instr.exec();
		}
	}
}
