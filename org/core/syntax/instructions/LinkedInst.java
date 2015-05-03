package org.core.syntax.instructions;

import org.core.syntax.Instruction;

public class LinkedInst implements Instruction {

	private Instruction current;
	private LinkedInst next;
	
	public LinkedInst(Instruction current, LinkedInst next) {
		this.current = current;
		this.next = next;
	}
	
	@Override
	public void exec() throws Exception {
		this.current.exec();
		if (this.next != null)
			this.next.exec();
	}
}
