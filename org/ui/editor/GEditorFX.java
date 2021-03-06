package org.ui.editor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;

public class GEditorFX extends BorderPane {
	
	private GTextEditorFX textEditor;
	private GTextInfosFX textInfos;
	
	public GEditorFX() {
		
		this.textEditor = new GTextEditorFX(this);

		/*String t = "var a;"
				+ "var b;"
				+ "start\n"
				+ GTextEditorFX.tabSpace + "a := 2;"
				+ GTextEditorFX.tabSpace + "b := 3;"
				+ GTextEditorFX.tabSpace + "if (a = 2) {\n"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "print a;"
				+ GTextEditorFX.tabSpace + "}\n"
				+ GTextEditorFX.tabSpace + "turn 4;"
				+ GTextEditorFX.tabSpace + "up;"
				+ GTextEditorFX.tabSpace + "forward b;"
				+ GTextEditorFX.tabSpace + "down;"
				+ GTextEditorFX.tabSpace + "forward b+7;"
				+ "end";*/
		String t = "start\n"
				+ GTextEditorFX.tabSpace + "def p(a, b) {\n"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "start\n"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "c := 1;"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "if (b > 0) {\n"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace +    "c := a * p(a, b-1);"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "} elif (b < 0 ) {\n"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace +   " c := 1 / p(a, 0-b);"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "}\n"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "end\n"
				+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "return c;"
	    	  + GTextEditorFX.tabSpace + "}\n"
	+ GTextEditorFX.tabSpace + "print p(2, 10);"
	+ GTextEditorFX.tabSpace + "down;"
	+ GTextEditorFX.tabSpace + "i := 0;"
	+ GTextEditorFX.tabSpace + "while (i < 32) {\n"
		+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "start\n"
		+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace +"i := i + 1;"
		+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "forward p(12/10, i);"
		+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "turn 90;"
		+ GTextEditorFX.tabSpace + GTextEditorFX.tabSpace + "end\n"
		+ GTextEditorFX.tabSpace + "}\n"
		+ "end";
		t = t.replace(";", ";\n");
		this.textEditor.getTextArea().setText(t);
		this.textEditor.setTabName("Example File");
		this.setCenter(textEditor);
		
		this.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				drawInfos();
			}
		});
		
		this.setPrefSize(0, 0);
		
	}
	
	public void drawInfos() {

		if (this.textEditor.getTabs().size() > 0) {
			this.textInfos = new GTextInfosFX(this.getWidth(), this.textEditor.getText(), this.textEditor.getCursorPosition());
			this.setBottom(this.textInfos);
		}
		else {
			this.textInfos = new GTextInfosFX(this.getWidth(), "", 0);
			this.setBottom(this.textInfos);
		}
		
	}
	
	public GTextEditorFX getTextEditor() {
		return this.textEditor;
	}
	
	public String getText() {
		return this.textEditor.getText();
	}
	
	public void newFile() {
		this.textEditor.newTab();
	}
	
}