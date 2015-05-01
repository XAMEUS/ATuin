package org.core.env;

public class Number {
	
	private java.lang.Number n;
	
	public Number() {
		this.n = 0;
	}
	
	public Number(java.lang.Number n) {
		this.n = n;
		this.check();
	}
	
	public boolean isInt() {
		return this.n.intValue() == this.n.doubleValue();
	}
	
	public boolean isDouble() {
		return !this.isInt();
	}
	
	public void toInt() {
		this.n = this.n.intValue();
	}
	
	private void check() {
		if (this.isInt())
			this.n = this.n.intValue();
	}
	
	public Number add(Number num) {
		Number N = new Number();
		N.n = this.n.doubleValue() + num.n.doubleValue();
		N.check();
		return N;
	}
	
	public Number subtract(Number num) {
		Number N = new Number();
		N.n = this.n.doubleValue() - num.n.doubleValue();
		N.check();
		return N;
	}
	
	public Number divide(Number num) {
		Number N = new Number();
		N.n = this.n.doubleValue() / num.n.doubleValue();
		N.check();
		return N;
	}
	
	public Number multiply(Number num) {
		Number N = new Number();
		N.n = this.n.doubleValue() * num.n.doubleValue();
		N.check();
		return N;
	}
	
	public boolean inferior(Number num) {
		return this.n.doubleValue() < num.n.doubleValue();
	}
	
	public boolean superior(Number num) {
		return this.n.doubleValue() > num.n.doubleValue();
	}
	
	public int intValue() {
		return this.n.intValue();
	}
	
	public int doubleValue() {
		return this.n.intValue();
	}
	
	public boolean equals(Object obj) {
		return this.n == ((Number)obj).n;
	};
	
	@Override
	public String toString() {
		return this.n.toString();
	}
}