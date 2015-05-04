package org.core.tests;

import org.core.env.Number;

public class NumberCoreTest {
	public static void main(String[] args) {
		Number a = new Number(4.0);
		Number b = new Number(4);
		java.lang.Number c = 4;
		System.out.println(a.equals(b));
		System.out.println(new Number(2).inferior(new Number(10)));
	}
}
