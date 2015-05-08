package org.ui.controller;

import org.core.env.Number;
import org.core.env.Point;
import org.core.turtle.LTurtle;

public class TurtleBrain {
	
	private static LTurtle turtle;
	
	public static void newTurtle() {
		TurtleBrain.turtle = new LTurtle();
	}
	
	public static void move(Number n) {
		double x = Controller.drawing.getWidth() / 1000;
		double y = Controller.drawing.getHeight() / 1000;
		Point p1 = new Point(turtle.getPosition());
		turtle.forward(n.doubleValue());
		Point p2 = new Point(turtle.getPosition());
		if (TurtleBrain.turtle.isDown())
			Controller.drawing.drawLine(x * p1.x, y * p1.y, x * p2.x, y * p2.y);
	}
	
	public static void turn(Number n) {
		double r = n.doubleValue();
		r = Math.PI * r / 180;
		turtle.turn(r);
	}
	
	public static void up() {
		TurtleBrain.turtle.up();
	}
	
	public static void down() {
		TurtleBrain.turtle.down();
	}
	
}
