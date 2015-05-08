package org.ui.menu;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import org.core.examples.Examples;
import org.ui.controller.Controller;
import org.ui.editor.GEditorFX;

public class GMenuFX extends HBox {
	
	private GEditorFX editor;
	private final MenuBar menuBar = new MenuBar();
	
	public GMenuFX(Stage primaryStage, GEditorFX editor) {
		
		Controller.editor = editor;
		this.editor = editor;
		
		this.createMenuFile();
		this.createMenuEdit();
		this.createMenuRun();
		createMenuSamples();
		this.createMenuHelp();
		
		FlowPane flow = new FlowPane(Orientation.HORIZONTAL);
		flow.setAlignment(Pos.TOP_RIGHT);
		flow.setHgap(0);
		//flow.setStyle("-fx-background-color: linear-gradient(to top, #262626, #2F2F2F)");
		BorderPane bp = new BorderPane();
		Image imageQuit = new Image(GMenuFX.class.getResource("iconmonstr-x-mark-icon-16.png").toString());
		Button quit = new Button("", new ImageView(imageQuit));
		//quit.setStyle("-fx-background-color: #111");
		quit.setMaxWidth(20);
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		bp.setCenter(quit);
		flow.getChildren().add(bp);
		
		this.setAlignment(Pos.CENTER_LEFT);
		HBox.setHgrow(flow, Priority.ALWAYS);
		
		this.getChildren().addAll(this.menuBar, flow);
		
	}

	private void createMenuFile() {
		
		Menu menu = new Menu("File");
		
		MenuItem newFile = new MenuItem("New             ");
		newFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.editor.newFile();
				Controller.out.println("New file created...");
			}
		});
		menu.getItems().add(newFile);
		
		menu.getItems().add(new SeparatorMenuItem());
		menu.getItems().add(new MenuItem("Load"));
		menu.getItems().add(new MenuItem("Save"));
		menu.getItems().add(new SeparatorMenuItem());
		menu.getItems().add(new MenuItem("Import"));
		menu.getItems().add(new MenuItem("Export"));
		
		this.menuBar.getMenus().add(menu);
	}


	private void createMenuEdit() {
		
		Menu menu = new Menu("Edit");
		
		menu.getItems().add(new MenuItem("Cut"));
		menu.getItems().add(new MenuItem("Copy"));
		menu.getItems().add(new MenuItem("Paste"));
		
		
		this.menuBar.getMenus().add(menu);
	}

	private void createMenuRun() {

		Menu menu = new Menu("Run");
		
		MenuItem run = new MenuItem("Run");
		run.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.run(editor);
			}
		});
		
		menu.getItems().add(run);
		menu.getItems().add(new MenuItem("Run + Interpreter"));
		
		this.menuBar.getMenus().add(menu);
		
	}
	
	private void createMenuSamples() {

		Menu menu = new Menu("Samples");
		
		MenuItem koch = new MenuItem("Koch");
		koch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.open(Examples.class.getResource("Flocon_Koch.txt").getFile());
			}
		});
		MenuItem dragon = new MenuItem("Dragon");
		dragon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.open(Examples.class.getResource("Dragon.txt").getFile());
			}
		});
		MenuItem tree = new MenuItem("Tree");
		tree.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.open(Examples.class.getResource("Tree.txt").getFile());
			}
		});
		
		menu.getItems().add(koch);
		menu.getItems().add(dragon);
		menu.getItems().add(tree);
		
		this.menuBar.getMenus().add(menu);
		
	}
	
	private void createMenuHelp() {

		Menu menu = new Menu("Help");
		
		menu.getItems().add(new MenuItem("Welcome"));
		menu.getItems().add(new MenuItem("Documentation"));
		menu.getItems().add(new MenuItem("About"));
		
		this.menuBar.getMenus().add(menu);
		
	}
	
}
