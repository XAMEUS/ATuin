package org.ui.console;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import org.fonts.Fonts;

public class GConsoleFX extends Tab {
	
	private static final TextArea textArea = new TextArea();
	
	public GConsoleFX() {
		
		this.setText("Console");
		this.setClosable(false);
		textArea.setFont(Font.loadFont(Fonts.class.getResource("LiberationMono-Regular.ttf").toString(), 12));
		textArea.getStyleClass().add("console");
		textArea.setEditable(false);
		BorderPane bp = new BorderPane();
		bp.getStyleClass().add("pane");
		bp.setCenter(textArea);
		this.setContent(bp);
		
		textArea.textProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue,
					Object newValue) {
				textArea.setScrollTop(Double.MAX_VALUE);
			}
		});

		textArea.setPrefSize(0, 1500);
		
	}
	
	public void print(String s) {
		textArea.appendText(s);
	}
	
	public void println(String s) {
		textArea.appendText(s + "\n");
	}
	
}
