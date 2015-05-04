package org.core.syntax.instructions;

import org.core.env.Values;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class Elif implements Instruction {
	
	private Expression expression;
	private Instruction inst;
	private Instruction next;
	
	public Elif(Expression expression, Instruction inst) {
		this.expression = expression;
		this.inst = inst;
		this.next = null;
	}
	
	public void add(Instruction next) {
		if (this.next == null) {
			this.next = next;
		}
		else {
			((Elif)(this.next)).add(next);
		}
	}

	@Override
	public void exec() throws Exception {
		if (this.expression.eval() == Values.TRUE.getValue()) {
			this.inst.exec();
		}
		else if (this.next != null) {
			this.next.exec();
		}
	}
	
}
