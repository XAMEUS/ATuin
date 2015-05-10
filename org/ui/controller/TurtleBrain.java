package org.ui.controller;

import org.core.env.Number;
import org.core.env.Point;
import org.core.turtle.LTurtle;

public class TurtleBrain {
	
	private static LTurtle turtle;
	
	public static void newTurtle() {
		TurtleBrain.turtle = new LTurtle();
	}
	
	public static void move(Number n) throws Exception {
		if (turtle.canMove(n.doubleValue())) {
			double x = Controller.drawing.getWidth() / 1000;
			double y = Controller.drawing.getHeight() / 1000;
			Point p1 = new Point(turtle.getPosition());
			turtle.forward(n.doubleValue());
			Point p2 = new Point(turtle.getPosition());
			if (TurtleBrain.turtle.isDown())
				Controller.drawing.drawLine(x * p1.x, y * p1.y, x * p2.x, y * p2.y);
		}
		else {
			throw new Exception("TurtleOutOfBound");
		}
	}
	
	public static void turn(Number n) {
		double r = n.doubleValue();
		r = - Math.PI * r / 180;
		turtle.turn(r);
	}
	
	public static void color(double r, double g, double b, double a) {
		if (r > 255) r = 255;
		if (r < 0) r = 0;
		if (g > 255) g = 255;
		if (g < 0) g = 0;
		if (b > 255) b = 255;
		if (b < 0) b = 0;
		if (a > 255) a = 255;
		if (a < 0) a = 0;
		Controller.drawing.setStroke(r/255, g/255, b/255, a/255);
	}
	
	public static void up() {
		TurtleBrain.turtle.up();
	}
	
	public static void down() {
		TurtleBrain.turtle.down();
	}
	
}
