package org.ui;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.ui.canvas.GDrawingFX;
import org.ui.console.GConsoleFX;
import org.ui.controller.Controller;
import org.ui.doc.GDocView;
import org.ui.editor.GEditorFX;
import org.ui.menu.GMenuFX;

public class RootLayout extends BorderPane {
	
	private GEditorFX editor;
	private double xOffset = 0;
	private double yOffset = 0;

	public RootLayout(Stage primaryStage) {
		
		GConsoleFX gc = new GConsoleFX();
		Controller.out = gc;
		
		this.editor = new GEditorFX();
		editor.getStyleClass().add("pane");
		GMenuFX menubar = new GMenuFX(primaryStage, editor);
		
		menubar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
			}
		});
		menubar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() + xOffset);
				primaryStage.setY(event.getScreenY() + yOffset);
			}
		});
		MenuBar m = new MenuBar();
		m.getMenus().add(new Menu("aze"));
		this.setTop(menubar);
		
		TabPaneWrapper wrapper = new TabPaneWrapper(Orientation.VERTICAL, .8);
		
		TabPane bottom = new TabPane();
		bottom.getStyleClass().add("pane");
		bottom.getTabs().add(Controller.out);
		bottom.getTabs().add(new GDocView());

		wrapper.addNodes(editor, bottom);

		TabPaneWrapper wrap = new TabPaneWrapper(Orientation.HORIZONTAL, .4);
		
		StackPane sp = new StackPane();
		GDrawingFX canvas = new GDrawingFX();
		Controller.drawing = canvas;
		sp.getChildren().add(canvas);
		
		wrap.addNodes(wrapper.getNode(), sp);

		this.setCenter(wrap.getNode());

	}
	
	public void drawInfos() {
		editor.drawInfos();
	}
	
}
