package org.core.env;

import java.util.HashMap;

public class ValueEnvionment {
	
	private static HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	public static void setValue(String name, int value) {
		ValueEnvionment.map.put(name, value);
	}
	
	public static int getValue(String name) {
		return ValueEnvionment.map.get(name);
	}
}
