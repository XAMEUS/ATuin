package org.core.env;

import java.util.HashMap;

public class ValueEnvionment {
	
	private HashMap<String, Number> map = new HashMap<String, Number>(256);
	
	public void setValue(String name, Number value) {
		this.map.put(name, value);
	}
	
	public Number getValue(String name) {
		return this.map.get(name);
	}
}
