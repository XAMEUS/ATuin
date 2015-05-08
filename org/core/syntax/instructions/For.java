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
		int n = exp.eval().intValue();
		for (int i = 0; i < n; i++) {
			if (instr != null)
				instr.exec();
		}
	}
}
