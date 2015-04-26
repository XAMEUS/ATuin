package org.ui.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ResizableCanvas extends Canvas {

	public ResizableCanvas() {
		widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());
	}

	private void draw() {
		
		 double width = getWidth();
         double height = getHeight();

         GraphicsContext gc = getGraphicsContext2D();
         gc.setFill(new Color(1, 1, 1, 1));
         gc.setStroke(new Color(0.98, 0.98, 0.98, 1));
         gc.clearRect(0, 0, width, height);
         gc.fillRect(0, 0, width, height);
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
