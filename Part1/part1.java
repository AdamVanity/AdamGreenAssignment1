/*
Assignment 1 part 1
@author Adam Green, 100653971
*/

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class part1 extends Application {
	public void start(Stage primaryStage) {

		HBox pane = new HBox(10);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5));

		for (int i = 0; i < 3; i++) {//display cards
			String cardNumber = Integer.toString((int)Math.floor(Math.random() * 52));
			ImageView cardImage = new ImageView("Cards/" + cardNumber + ".png");
			cardImage.setPreserveRatio(true);
			cardImage.setFitHeight(200);
			pane.getChildren().add(cardImage);
		}

		Scene scene = new Scene(pane);//setting scene
    primaryStage.setScene(scene);
		primaryStage.setTitle("Part1");
		primaryStage.show();
	}
}
