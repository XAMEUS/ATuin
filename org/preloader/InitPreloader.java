package org.preloader;

import org.ui.style.Style;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InitPreloader extends Preloader {
	
    ProgressBar bar;
    Stage stage;
    boolean noLoadingProgress = true;
 
    private Scene createPreloaderScene() {
        bar = new ProgressBar(0);
        BorderPane p = new BorderPane();
        p.setCenter(bar);
        Scene s = new Scene(p, 400, 150);
        return s;
    }
 
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createPreloaderScene());
        InitPreloader.setUserAgentStylesheet(Style.class.getResource("style.css").toString());
        stage.show();
    }
 
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
    	bar.setProgress(pn.getProgress());
    }
 
    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
    	if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
 
    @Override
    public void handleApplicationNotification(PreloaderNotification pn) {
        if (pn instanceof ProgressNotification) {
           double v = ((ProgressNotification) pn).getProgress();
           bar.setProgress(v);            
        } else if (pn instanceof StateChangeNotification) {
            stage.hide();
        }
    }
 }