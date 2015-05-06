package org.core.env;

public class Vector {

	public double x;
	public double y;

	public Vector() {
		this.x = 0;
		this.y = 0;
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void rotate(double n) {
		double xx = (this.x * Math.cos(n)) - (this.y * Math.sin(n));
		double yy = (this.x * Math.sin(n)) + (this.y * Math.cos(n));
		x = xx;
		y = yy;
	}
	
	public void normalize() {
		this.x /= this.norm();
		this.y /= this.norm();
	}
	
	public double norm() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

}
