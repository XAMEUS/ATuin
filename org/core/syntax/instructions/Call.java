package org.core.syntax.instructions;

import org.core.env.Envionment;
import org.core.env.Number;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;

public class Call implements Instruction, Expression {
	
	private String fname;
	private Expression[] args;
	
	public Call(String fname) {
		this.fname = fname;
	}
	
	public void setArgs(Expression[] args) {
		this.args = args;
	}
	
	@Override
	public void exec() throws Exception {
		Number[] args = new Number[this.args.length];
		for (int i = 0; i < args.length; i++)
			args[i] = this.args[i].eval();
		String last = Envionment.getEnvionment();
		Envionment.setEnvionment(this.fname + System.nanoTime());
		Envionment.getFunction(this.fname).call(args);
		Envionment.setEnvionment(last);
	}

	@Override
	public Number eval() throws Exception {
		Number[] args = new Number[this.args.length];
		for (int i = 0; i < args.length; i++)
			args[i] = this.args[i].eval();
		String last = Envionment.getEnvionment();
		Envionment.setEnvionment(this.fname +  + System.nanoTime());
		Envionment.getFunction(this.fname).call(args);
		Number n = Envionment.getFunction(this.fname).eval();
		Envionment.cleanEnvionment();
		Envionment.setEnvionment(last);
		return n;
	}

}
