package org.ui.editor;

import org.ui.canvas.ResizableCanvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GTextInfosFX extends ResizableCanvas {
	
	private double width;
	private String text;
	private int pos;
	
	public GTextInfosFX(double w, String text, int pos) {
		this.width = w;
		this.text = text;
		this.pos = pos;
		this.canvas.setWidth(w);
		this.canvas.setHeight(20);
		this.draw(text, pos);
		this.setMinWidth(260);
	}
	
	public void draw() {
		this.draw(text, pos);
	}
	
	private void draw(String t, int pos) {
		
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.setFill(new Color(0, 0, 0, 1));
		
		int l = 0;
		int c = 0;
		for (int i = 0; i < pos; i++) {
			if(t.charAt(i) == '\n') {
				l += 1;
				c = 0;
			}
			else c++;
		}
		
		gc.setFill(new Color(0.4, 0.4, 0.4, 1));
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.fillText("chars: " + t.length() + "  |  lines: " + t.split("\r\n|\r|\n").length, this.width - 5, 14, this.width);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.fillText("line: " + l + "  |  col: " + c + "  |  char: " + pos , 5, 14, this.width);
	}

}
