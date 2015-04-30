package org.core.env;

public enum Values {
	TRUE (1),
	FALSE (0);
	private int value;
	private Values(int value) {
		this.value = value;
	}
	public int getValue() {
        return value;
    }
}
