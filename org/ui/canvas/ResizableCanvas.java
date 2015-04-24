package org.ui.canvas;

import javafx.scene.canvas.Canvas;

public class ResizableCanvas extends Canvas {
	
	public ResizableCanvas() {
		// TODO Auto-generated constructor stub
	}
	
	private void draw() {
		// TODO
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public double prefWidth(double height) {
		return this.getWidth();
	}
	
	@Override
	public double prefHeight(double width) {
		return this.getHeight();
	}

}
