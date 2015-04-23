package org.ui;

import org.ui.console.GConsoleFX;
import org.ui.controller.Controller;
import org.ui.doc.GDocView;
import org.ui.editor.GEditorFX;
import org.ui.menu.GMenuFX;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
		
		this.setCenter(wrapper.getNode());
		
	}
	
	public void drawInfos() {
		editor.drawInfos();
	}
	
}


class TabPaneWrapper {
	
	private SplitPane split;

	public TabPaneWrapper(Orientation o, double splitLocation) {
		split = new SplitPane();
		split.setOrientation(o);
		split.setDividerPosition(0, splitLocation);
	}

	public void addNodes(final Node node1, final Node node2) {
		split.getItems().addAll(node1, node2);
	}

	public Parent getNode() {
		return split;
	}
}