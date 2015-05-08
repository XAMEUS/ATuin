package org.ui.editor;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import org.fonts.Fonts;

public class GTextEditorFX extends TabPane {
	
	private GEditorFX gefx;
	public final static String tabSpace = "   ";
	
	private class TextTab extends Tab {
		
		private TextArea textArea;
		
		public TextTab(String text) {
			
			this.setText(text);
			
			this.textArea = new TextArea();
			textArea.addEventFilter(
				KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {
					final KeyCombination combo = new KeyCodeCombination(
							KeyCode.TAB);

					@Override
					public void handle(KeyEvent event) {
						if (combo.match(event)) {
							textArea.insertText(
									textArea.getCaretPosition(), tabSpace);
							event.consume();
						}
					}
				}
			);
			this.textArea.getStyleClass().add("console");
			this.textArea.setFont(Font.loadFont(Fonts.class.getResource("LiberationMono-Regular.ttf").toString(), 12));
			
			this.createActions();

			BorderPane content = new BorderPane();
			content.setCenter(this.textArea);
			this.setContent(content);
		}
		
		private void createActions() {
			
			this.textArea.setOnKeyReleased(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					gefx.drawInfos();
				}
			});
			
			this.textArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					gefx.drawInfos();
				}
			});
			
		}
		
		public TextArea getTextArea() {
			return this.textArea;
		}
		
	}
	
	public GTextEditorFX(GEditorFX gefx) {
		this.gefx = gefx;
		this.newTab();
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gefx.drawInfos();
			}
		});
	}
	
	public void newTab() {
		this.newTab("New File");
	}
	
	public void newTab(String name) {

		Tab tab = new TextTab(name);
		this.getTabs().add(tab);
		
	}
	
	public void newTab(String name, String text) {
		
		TextTab tab = new TextTab(name);
		tab.getTextArea().setText(text);
		this.getTabs().add(tab);
		
	}
	
	public void setTabName(String name) {
		for (Tab t : this.getTabs())
			if (t.isSelected())
				t.setText(name);
	}
	
	public String getText() {
		for (Tab t : this.getTabs())
			if (t.isSelected())
				return ((TextTab)t).getTextArea().getText();
		return null;
	}
	
	public int getCursorPosition() {
		for (Tab t : this.getTabs())
			if (t.isSelected())
				return ((TextTab)t).getTextArea().getCaretPosition();
		return 0;
	}
	
	public TextArea getTextArea() {
		for (Tab t : this.getTabs())
			if (t.isSelected())
				return ((TextTab)t).getTextArea();
		return null;
	}
	
}
