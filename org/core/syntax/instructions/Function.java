package org.core.syntax.instructions;

import java.util.ArrayList;

import org.core.env.Envionment;
import org.core.syntax.Instruction;
import org.core.syntax.expressions.Variable;

public class Function extends Instruction {
	
	private String name;
	private ArrayList<Variable> args = new ArrayList<Variable>();
	private Instruction instr;
	
	public Function(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addArg(Variable arg) {
		this.args.add(arg);
	}
	
	public void setInstr(Instruction instr) {
		this.instr = instr;
	}
	
	@Override
	public void exec() throws Exception {
		Envionment.addFunction(this);
	}
	
	public void call() {
		
	}

}
