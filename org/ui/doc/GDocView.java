package org.ui.doc;

import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GDocView extends Tab {
	
	private final BorderPane content = new BorderPane();
	private final WebView webView = new WebView();
	private final WebEngine webEngine = webView.getEngine();
	
	public GDocView() {
		this.setText("Documentation");
		this.setClosable(false);
		this.setContent(content);
		this.content.getStyleClass().add("doc");
		this.content.setCenter(new AnchorPane(webView));
		this.webView.setPrefSize(0, 0);
		System.out.println(Doc.getRessource("index.html"));
		this.webEngine.load(Doc.getRessource("index.html"));	
	}
	
}
