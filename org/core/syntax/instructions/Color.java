package org.core.syntax.instructions;

import org.core.syntax.Expression;
import org.core.syntax.Instruction;
import org.ui.controller.TurtleBrain;

public class Color implements Instruction {
	
	private Expression r, g, b, a;
	
	public Color(Expression r, Expression g, Expression b, Expression a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	@Override
	public void exec() throws Exception {
		TurtleBrain.color(r.eval().doubleValue(), g.eval().doubleValue(), b.eval().doubleValue(), a.eval().doubleValue());
	}

}
