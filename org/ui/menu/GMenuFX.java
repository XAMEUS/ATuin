package org.ui.menu;


import java.awt.Desktop;
import java.io.File;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.ui.controller.Controller;
import org.ui.editor.GEditorFX;

public class GMenuFX extends MenuBar {
	
	private GEditorFX editor;
	
	public GMenuFX(Stage primaryStage, GEditorFX editor) {
		
		Controller.editor = editor;
		this.editor = editor;
		
		this.createMenuFile();
		//this.createMenuEdit();
		this.createMenuRun();
		this.createMenuSamples();
		this.createMenuHelp();
		
		/*FlowPane flow = new FlowPane(Orientation.HORIZONTAL);
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
		
		//this.setAlignment(Pos.CENTER_LEFT);
		HBox.setHgrow(flow, Priority.ALWAYS);*/
		
		//this.getChildren().addAll(this);
		
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
		MenuItem load = new MenuItem("Load");
		load.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Show open file dialog
				File file = fileChooser.showOpenDialog(Controller.primaryStage);

				if (file != null) {
					Controller.open(file.getAbsolutePath());
				}
			}
		});
		MenuItem save = new MenuItem("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Show save file dialog
				File file = fileChooser.showSaveDialog(Controller.primaryStage);

				if (file != null) {
					Controller.save_file(editor, file.getAbsolutePath());
				}
			}
		});
		MenuItem impo = new MenuItem("Import");
		impo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Show open file dialog
				File file = fileChooser.showOpenDialog(Controller.primaryStage);

				if (file != null) {
					Controller.last_instruction = Controller.load_build(file.getAbsolutePath());
					Controller.out.println(Controller.last_instruction + " loaded");
				}
			}
		});
		MenuItem export = new MenuItem("Export");
		export.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Show save file dialog
				File file = fileChooser.showSaveDialog(Controller.primaryStage);

				if (file != null) {
					Controller.save_build(Controller.last_instruction, file.getAbsolutePath());
				}
			}
		});
		MenuItem quit = new MenuItem("Quit");
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		menu.getItems().add(newFile);
		menu.getItems().add(new SeparatorMenuItem());
		menu.getItems().add(load);
		menu.getItems().add(save);
		menu.getItems().add(new SeparatorMenuItem());
		menu.getItems().add(impo);
		menu.getItems().add(export);
		menu.getItems().add(new SeparatorMenuItem());
		menu.getItems().add(quit);
		
		this.getMenus().add(menu);
	}


	private void createMenuEdit() {
		
		Menu menu = new Menu("Edit");
		
		menu.getItems().add(new MenuItem("Cut     "));
		menu.getItems().add(new MenuItem("Copy"));
		menu.getItems().add(new MenuItem("Paste"));
		
		
		this.getMenus().add(menu);
	}

	private void createMenuRun() {

		Menu menu = new Menu("Run");
		
		MenuItem run = new MenuItem("Run     ");
		run.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.run(editor);
			}
		});
		
		menu.getItems().add(run);
		
		MenuItem call = new MenuItem("Call");
		call.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.recall();
			}
		});
		menu.getItems().add(call);
		
		this.getMenus().add(menu);
		
	}
	
	private void createMenuSamples() {

		Menu menu = new Menu("Samples");
		
		MenuItem koch = new MenuItem("Koch    ");
		koch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.open("samples/Flocon_Koch.txt");
			}
		});
		MenuItem dragon = new MenuItem("Dragon");
		dragon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.open("samples/Dragon.txt");
			}
		});
		MenuItem tree = new MenuItem("Tree");
		tree.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Controller.open("samples/Tree.txt");
			}
		});
		
		menu.getItems().add(koch);
		menu.getItems().add(dragon);
		menu.getItems().add(tree);
		
		this.getMenus().add(menu);
		
	}
	
	private void createMenuHelp() {

		Menu menu = new Menu("Help");
		
		MenuItem welcome = new MenuItem("Welcome");
		welcome.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			            desktop.browse(new URI("http://xameus.github.io/LogoTurtle/welcome.html"));
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			}
		});
		MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			            desktop.browse(new URI("http://xameus.github.io/LogoTurtle/features.html"));
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			}
		});
		menu.getItems().add(welcome);
		//menu.getItems().add(new MenuItem("Documentation  "));
		menu.getItems().add(about);
		
		this.getMenus().add(menu);
		
	}
	
}
