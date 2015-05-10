package org.core.turtle;

import org.core.env.Point;
import org.core.env.Vector;

public class LTurtle {
	
	private Point point;
	private Vector orientation;
	private boolean status = false; // true : DOWN, false : UP
	
	public LTurtle() {
		this.orientation = new Vector(1, 0);
		this.point = new Point();
		this.point.x = 500;
		this.point.y = 500;
	}
	
	public void toCenter() {
		this.point.x = 500;
		this.point.y = 500;
	}
	
	public void forward(double d) {
		this.orientation.norm();
		this.point.x += this.orientation.x * d;
		this.point.y += this.orientation.y * d;
	}
	
	public void turn(double r) {
		this.orientation.rotate(r);
	}
	
	public Point getPosition() {
		return this.point;
	}

	public void up() {
		this.status = false;
	}

	public void down() {
		this.status = true;
	}
	
	public boolean isDown() {
		return this.status;
	}

	public boolean canMove(int d) {
		double vx = this.point.x + this.orientation.x * d;
		double vy = this.point.y + this.orientation.y * d;
		return 	vx < 1000 && vy < 1000 && vx > 0 && vy > 0;
	}
	
}
