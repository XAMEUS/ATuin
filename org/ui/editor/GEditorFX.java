package org.ui.editor;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import org.fonts.Fonts;

public class GEditorFX extends BorderPane {
	
	private TextEditor textEditor;
	private TextInfos textInfos;
	
	public GEditorFX() {
		
		this.textEditor = new TextEditor(this);

		String t = "Var angle;\n"
				+ "Debut\n"
				+ "angle = 0-90;"
				+ "BasPinceau;"
				+ "Avance 10;"
				+ "Tourne angle;"
				+ "Avance 10;"
				+ "Tourne angle;"
				+ "Avance 10;"
				+ "Tourne angle;"
				+ "Avance 10;"
				+ "Fin";
		t = t.replace(";", ";\n");
		this.textEditor.getTextArea().setText(t);
		this.textEditor.setTabName("Example File");
		this.setCenter(textEditor);
		
	}
	
	public void drawInfos() {
		
		if (this.textEditor.getTabs().size() > 0) {
			this.textInfos = new TextInfos(this.getWidth(), this.textEditor.getText(), this.textEditor.getCursorPosition());
			this.setBottom(this.textInfos);
		}
		else {
			this.textInfos = new TextInfos(this.getWidth(), "", 0);
			this.setBottom(this.textInfos);
		}
		
	}
	
	public void newFile() {
		this.textEditor.newTab();
	}
	
}

class TextEditor extends TabPane {
	
	private GEditorFX gefx;
	
	private class TextTab extends Tab {
		
		private TextArea textArea;
		
		public TextTab(String text) {
			
			this.setText(text);
			
			this.textArea = new TextArea();
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
	
	public TextEditor(GEditorFX gefx) {
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

class TextInfos extends Canvas {
	
	private double width;
	
	public TextInfos(double w, String text, int pos) {
		this.width = w;
		this.setWidth(w);
		this.setHeight(20);
		this.draw(text, pos);
	}
	
	public void draw(String t, int pos) {
		
		GraphicsContext gc = this.getGraphicsContext2D();
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