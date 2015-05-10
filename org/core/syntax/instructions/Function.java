package org.core.syntax.instructions;


import org.core.env.Envionment;
import org.core.env.Number;
import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.core.syntax.Method;

public class Function implements Instruction, Expression, Method {
	
	private String name;
	private String[] args;
	private Instruction instr;
	private Expression rreturn;
	
	public Function(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	public String[] getArgs() {
		return this.args;
	}
	
	public void setInstr(Instruction instr) {
		this.instr = instr;
	}
	
	public void setReturn(Expression exp) {
		this.rreturn = exp;
	}
	
	@Override
	public void exec() throws Exception {
		Envionment.addFunction(this);
	}
	
	@Override
	public Number eval() throws Exception {
		return this.rreturn.eval();
	}

	@Override
	public void call(Number[] args) throws Exception {
		if (this.args != null) {
			if (args.length != this.args.length)
				throw new Exception("Incorrects arguments...");
			for (int i = 0; i < this.args.length; i++) {
				Envionment.setValue(this.args[i], args[i]);
			}
		}
		this.instr.exec();
	}

}
