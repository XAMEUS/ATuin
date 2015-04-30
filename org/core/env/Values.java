package org.core.env;

public enum Values {
	TRUE (new Number(1)),
	FALSE (new Number(0));
	private Number value;
	private Values(Number value) {
		this.value = value;
	}
	public Number getValue() {
        return value;
    }
}
