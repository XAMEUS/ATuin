package org.core.syntax.instructions;

import org.core.syntax.Instruction;
import org.ui.controller.TurtleBrain;

public class Down implements Instruction {

	@Override
	public void exec() throws Exception {
		TurtleBrain.down();
	}

}
