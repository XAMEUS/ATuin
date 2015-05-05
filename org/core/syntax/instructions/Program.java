package org.core.syntax.instructions;

import org.core.syntax.Instruction;

public class Program implements Instruction {
	
	private Decl decl;
	private Instruction inst;
	
	public Program(Decl decl, Instruction inst) {
		this.decl = decl;
		this.inst = inst;
	}
	
	@Override
	public void exec() throws Exception {
		if (this.decl != null)
			this.decl.exec();
		this.inst.exec();
	}

}
