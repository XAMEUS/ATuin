package org.core.syntax.instructions;

import org.core.env.ValueEnvionment;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class Assign extends Instruction {
	
	private String name;
	private Expression expression;
	
	public Assign(String name, Expression exp) {
		this.name = name;
		this.expression = exp;
	}
	
	@Override
	public void exec() throws Exception {
		ValueEnvionment.setValue(this.name, this.expression.eval());
	}

}
