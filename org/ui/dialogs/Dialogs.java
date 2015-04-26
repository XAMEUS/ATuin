package org.ui.dialogs;

import javax.swing.GroupLayout.Alignment;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dialogs {
	
	public static void showMessage(Stage primaryStage, String msg) {
		final Stage dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        BorderPane bp = new BorderPane();
        StackPane sp = new StackPane();
        sp.setPrefSize(0, 50);
        sp.setStyle("-fx-background-color: #ee3311");
        Text t = new Text(msg);
        t.setStrokeWidth(4);
        sp.getChildren().add(t);
        bp.setTop(sp);
        TextArea ta = new TextArea();
        ta.setEditable(false);
        bp.setCenter(ta);
        Button b = new Button("close");
        //bp.setBottom(b);
        bp.setAlignment(b, Pos.BOTTOM_RIGHT);
        Scene dialogScene = new Scene(bp, 300, 200);
        dialog.setTitle("Error");
        dialog.setScene(dialogScene);
        dialog.setResizable(false);
        dialog.show();
	}
	
}
