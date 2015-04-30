package org.core.syntax;

import org.core.env.Number;

public abstract class Expression {
	public abstract Number eval() throws Exception;
}
