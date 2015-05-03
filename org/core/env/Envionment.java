package org.core.env;

import java.util.HashMap;

import org.core.syntax.instructions.Function;

public class Envionment {
	
	private static HashMap<String, ValueEnvionment> map0 = new HashMap<String, ValueEnvionment>();
	private static HashMap<String, Function> map1 = new HashMap<String, Function>();
	private static String status;
	
	public static String getEnvionment() {
		return Envionment.status;
	}
	
	public static void setEnvionment(String name) {
		Envionment.status = name;
		if (! Envionment.map0.containsKey(name))
			Envionment.map0.put(name, new ValueEnvionment());
	}
	
	public static void addFunction(Function f) {
		Envionment.map1.put(f.getName(), f);
	}
	
	public static Function getFunction(String name) {
		return Envionment.map1.get(name);
	}
	
	public static Number getValue(String name) {
		return Envionment.map0.get(Envionment.status).getValue(name);
	}
	
	public static void setValue(String name, Number value) {
		Envionment.map0.get(Envionment.status).setValue(name, value);
	}
	
}
