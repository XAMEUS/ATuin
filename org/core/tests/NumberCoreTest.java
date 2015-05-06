package org.core.tests;

import javafx.geometry.Point2D;

import org.core.env.Number;

import com.sun.javafx.geom.Vec2d;

public class NumberCoreTest {
	public static void main(String[] args) {
		Number a = new Number(4.0);
		Number b = new Number(4);
		java.lang.Number c = 4;
		//System.out.println(a.equals(b));
		//System.out.println(new Number(2).inferior(new Number(10)));
		Point2D p = new Point2D(0, 0);
		Vec2d v = new Vec2d(2, 0);
	}
}
