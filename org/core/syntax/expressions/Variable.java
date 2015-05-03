package org.core.syntax.expressions;

import org.core.env.Envionment;
import org.core.env.Number;
import org.core.syntax.Expression;

public class Variable implements Expression {
	
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	@Override
	public Number eval() throws Exception {
		return Envionment.getValue(this.name);
	}

}
