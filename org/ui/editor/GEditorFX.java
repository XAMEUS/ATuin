package org.ui.editor;

import javafx.scene.layout.BorderPane;

public class GEditorFX extends BorderPane {
	
	private GTextEditorFX textEditor;
	private GTextInfosFX textInfos;
	
	public GEditorFX() {
		
		this.textEditor = new GTextEditorFX(this);

		String t = "var a;"
				+ "var b;"
				+ "start\n"
				+ GTextEditorFX.tabSpace + "a = 2;"
				+ GTextEditorFX.tabSpace + "b = 3;"
				+ GTextEditorFX.tabSpace + "turn 4;"
				+ GTextEditorFX.tabSpace + "up;"
				+ GTextEditorFX.tabSpace + "forward b;"
				+ GTextEditorFX.tabSpace + "down;"
				+ GTextEditorFX.tabSpace + "forward b+7;"
				+ "end";
		t = t.replace(";", ";\n");
		this.textEditor.getTextArea().setText(t);
		this.textEditor.setTabName("Example File");
		this.setCenter(textEditor);
		
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
	
	public void newFile() {
		this.textEditor.newTab();
	}
	
}