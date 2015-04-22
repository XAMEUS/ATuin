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
				+ "\ta = 2;"
				+ "\tb = 3;"
				+ "\tturn 4;"
				+ "\tup;"
				+ "\tforward b;"
				+ "\tdown;"
				+ "\tforward b+7;"
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