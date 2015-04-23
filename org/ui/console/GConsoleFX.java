package org.ui.console;

import org.fonts.Fonts;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class GConsoleFX extends Tab {
	
	private static final TextArea textArea = new TextArea();
	
	public GConsoleFX() {
		
		this.setText("Console");
		this.setClosable(false);
		textArea.setFont(Font.loadFont(Fonts.class.getResource("LiberationMono-Regular.ttf").toString(), 12));
		textArea.getStyleClass().add("console");
		BorderPane bp = new BorderPane();
		bp.getStyleClass().add("pane");
		bp.setCenter(textArea);
		this.setContent(bp);
		
	}
	
	public void print(String s) {
		textArea.setText(textArea.getText()+s);
		textArea.setScrollTop(Double.MAX_VALUE);
	}
	
	public void println(String s) {
		textArea.setText(textArea.getText()+s+"\n");
		textArea.setScrollTop(Double.MAX_VALUE);
	}
	
}
