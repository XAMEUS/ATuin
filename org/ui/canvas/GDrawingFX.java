package org.ui.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GDrawingFX extends ResizableCanvas {

	@Override
	public void draw() {
		
		final double width = this.canvas.getWidth();
    	final double height = this.canvas.getHeight();
    	
    	GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.setFill(new Color(1, 1, 1, 1));
		gc.setStroke(new Color(0.9, 0.9, 0.9, 1));
		gc.clearRect(0, 0, width, height);
		gc.fillRect(0, 0, width, height);
		gc.setLineWidth(4);
		gc.strokeLine(0, 0, width, height);
		gc.strokeLine(width, 0, 0, height);
	}

}
