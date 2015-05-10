package org.core.syntax;

import java.io.Serializable;

import org.core.env.Number;

public interface Expression extends Serializable {
	public Number eval() throws Exception;
}
