package org.core.syntax.instructions;

import org.core.env.Number;
import org.core.env.ValueEnvionment;
import org.core.syntax.Instruction;

public class Decl extends Instruction {
	
	private String name;
	private Decl next;
	
	public Decl(String name, Decl next) {
		this.name = name;
		this.next = next;
	}
	
	@Override
	public void exec() throws Exception {
		ValueEnvionment.setValue(this.name, new Number());
		if (this.next != null)
			this.next.exec();
	}

}
