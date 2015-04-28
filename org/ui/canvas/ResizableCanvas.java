package org.ui.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public abstract class ResizableCanvas extends Pane {

    protected final Canvas canvas = new Canvas();

    public ResizableCanvas() {
        getChildren().add(canvas);
    }

    @Override
    protected void layoutChildren() {
        final int top = (int)snappedTopInset();
        final int right = (int)snappedRightInset();
        final int bottom = (int)snappedBottomInset();
        final int left = (int)snappedLeftInset();
        final int w = (int)getWidth() - left - right;
        final int h = (int)getHeight() - top - bottom;
        canvas.setLayoutX(left);
        canvas.setLayoutY(top);
        if (w != canvas.getWidth() || h != canvas.getHeight()) {
            canvas.setWidth(w);
            canvas.setHeight(h);
            this.draw();
        }
    }
    
    public abstract void draw();
    
    @Override
	public boolean isResizable() {
		return true;
	}

}
