package org.ui.canvas;

import org.ui.controller.Controller;

import javafx.geometry.Point2D;
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
		
		Controller.recall();
		
	}
	
	public void drawLine(double x1, double y1, double x2, double y2) {
    	GraphicsContext gc = this.canvas.getGraphicsContext2D();
    	gc.setStroke(new Color(1, 0, 0, 1));
    	gc.strokeLine(x1, y1, x2, y2);
	}
	
	public void drawLine(Point2D p1, Point2D p2) {
    	GraphicsContext gc = this.canvas.getGraphicsContext2D();
    	gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

}
