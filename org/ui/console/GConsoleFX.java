package org.ui.console;

import org.fonts.Fonts;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class GConsoleFX extends Tab {
	
	private final TextArea textArea = new TextArea();
	
	public GConsoleFX() {
		
		this.setText("Console");
		this.setClosable(false);
		this.textArea.setFont(Font.loadFont(Fonts.class.getResource("LiberationMono-Regular.ttf").toString(), 12));
		this.textArea.getStyleClass().add("console");
		BorderPane bp = new BorderPane();
		bp.getStyleClass().add("pane");
		bp.setCenter(this.textArea);
		this.setContent(bp);
		
	}
	
	public void print(String s) {
		this.textArea.setText(this.getText()+s);
		this.textArea.setScrollTop(Double.MAX_VALUE);
	}
	
	public void println(String s) {
		this.textArea.setText(this.getText()+s+"\n");
		this.textArea.setScrollTop(Double.MAX_VALUE);
	}
	
}