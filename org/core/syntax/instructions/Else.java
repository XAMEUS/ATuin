package org.core.syntax.instructions;

import org.core.syntax.Instruction;

public class Else implements Instruction {
	
	private Instruction inst;
	
	public Else(Instruction inst) {
		this.inst = inst;
	}

	@Override
	public void exec() throws Exception {
		this.inst.exec();
	}

}
