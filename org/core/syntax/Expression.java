package org.core.syntax;

import org.core.env.Number;

public interface Expression {
	public Number eval() throws Exception;
}
