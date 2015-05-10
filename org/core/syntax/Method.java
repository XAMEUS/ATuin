package org.core.syntax;

import java.io.Serializable;

import org.core.env.Number;

public interface Method extends Serializable {
	public void call(Number[] n) throws Exception;
}
