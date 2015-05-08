package org.ui;

import org.img.Img;
import org.ui.controller.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primarySage;
	private RootLayout rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.primarySage = primaryStage;
		this.primarySage.setTitle("A'Tuin");
		this.primarySage.getIcons().add(new Image(Img.class.getResource("logo4.png").toString()));
		this.primarySage.setWidth(850);
		this.primarySage.setHeight(600);
		//this.primarySage.initStyle(StageStyle.TRANSPARENT);
		Controller.primaryStage = primaryStage;
		
		this.rootLayout = new RootLayout(this.primarySage);
		
		//this.rootLayout.getStylesheets().add(Main.class.getResource("/org/ui/style/style.css").toString());
		this.rootLayout.getStyleClass().add("pane");
		
		Scene scene = new Scene(this.rootLayout);
		scene.setFill(Color.TRANSPARENT);
		
		rootLayout.getStylesheets().add(Main.class.getResource("/org/ui/style/default.css").toString());
		
		this.primarySage.setScene(scene);
		this.primarySage.show();
		
		this.rootLayout.drawInfos();
		
	}
	
	public static void main(String[] args) {
		
		Main.launch(args);
        
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        	public void run() {
        		System.out.println("[QUIT]");
        	}
        }));
	}
	
}
