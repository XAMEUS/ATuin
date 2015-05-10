package org.core.syntax;

import java.io.Serializable;

public interface Instruction extends Serializable {
	public void exec() throws Exception;
}
