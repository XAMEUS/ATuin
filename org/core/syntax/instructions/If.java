package org.core.syntax.instructions;

import org.core.env.Values;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class If extends Instruction {
	
	private Expression expression;
	private Instruction inst;
	private Elif Elif;
	private Else Else;
	
	public If(Expression expression, Instruction inst) {
		this.expression = expression;
		this.inst = inst;
		this.Elif = null;
		this.Else = null;
	}
	
	public void add(Expression expression, Instruction inst) {
		if (expression != null) {
			Elif c = new Elif(expression, inst);
			if (this.Elif == null)
				this.Elif = c;
			else
				this.Elif.add(c);
		}
		else {
			Else e = new Else(inst);
			if (this.Elif == null)
				this.Else = e;
			else
				this.Elif.add(e);
		}
	}

	@Override
	public void exec() throws Exception {
		if (this.expression.eval() == Values.TRUE.getValue())
			this.inst.exec();
		else if (this.Elif != null)
			this.Elif.exec();
		else if (this.Else != null)
			this.Else.exec();
	}
	
}
