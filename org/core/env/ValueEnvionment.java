package org.core.env;

import java.util.HashMap;

public class ValueEnvionment {
	
	private static HashMap<String, Number> map = new HashMap<String, Number>();
	
	public static void setValue(String name, Number value) {
		ValueEnvionment.map.put(name, value);
	}
	
	public static Number getValue(String name) {
		return ValueEnvionment.map.get(name);
	}
}
