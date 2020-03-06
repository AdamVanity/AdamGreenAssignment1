/*
Assignment 1 part 3
@author Adam Green, 100653971
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class part3 extends Application {

	public void start(Stage primaryStage) {
		//initialize pane
		Pane pane = new PaneWithTriangle();
		//new scene
		Scene scene = new Scene(pane, 400, 450);
		//display
		primaryStage.setTitle("Part3");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static class PaneWithTriangle extends Pane {//triangle pane

		private Circle mainCirc;//Main circle
		private Circle [] outerCirc = new Circle[3];//perimeter circles
		private Label [] labl = new Label[3];//labels for angles
		private Line [] line = new Line[3];//lines between perimeter circles

		PaneWithTriangle() {
			//background info
			setBackground(new Background(new BackgroundFill(Color.TURQUOISE,
																		CornerRadii.EMPTY, Insets.EMPTY)));

			//create main circle
			mainCirc = new Circle(200, 200, 180);
			mainCirc.setStroke(Color.BLACK);
			mainCirc.setFill(null);

			//creating perimeter circles
			for(int i = 0; i < 3; i++){
				outerCirc[i] = new Circle(10);
				outerCirc[i].setCenterY(mainCirc.getCenterY() - mainCirc.getRadius() * Math.sin(i + 1));
				outerCirc[i].setCenterX(mainCirc.getCenterX() + mainCirc.getRadius() * Math.cos(i + 1));
				outerCirc[i].setStroke(Color.BLACK);
				outerCirc[i].setFill(Color.RED);
			}
			//creating connecting lines and labels for angles
			for(int i = 0; i < 3; i++){
				line[i] = new Line();
				line[i].startYProperty().bind(outerCirc[i].centerYProperty());
				line[i].startXProperty().bind(outerCirc[i].centerXProperty());
				if (i < 2) {
					line[i].endYProperty().bind(outerCirc[i + 1].centerYProperty());
					line[i].endXProperty().bind(outerCirc[i + 1].centerXProperty());
				} else {
					line[i].endYProperty().bind(outerCirc[i - 2].centerYProperty());
					line[i].endXProperty().bind(outerCirc[i - 2].centerXProperty());
				}
				labl[i] = new Label();
				labl[i].setFont(Font.font(18));
				labl[i].layoutYProperty().bind(outerCirc[i].centerYProperty().add(5));
				labl[i].layoutXProperty().bind(outerCirc[i].centerXProperty().add(5));
			}
			//event handlers for outer circles
			for (int i = 0; i < 3 ; i++) {
				moveCircles(outerCirc[i]);
			}
			getChildren().addAll(mainCirc, line[0], line[1], line[2],
														outerCirc[0], outerCirc[1], outerCirc[2],
														labl[0], labl[1], labl[2]);
			refreshLabels();
		}
		//lengths
		private double length(Circle circ1, Circle circ2) {
			return Math.sqrt(Math.pow(circ1.getCenterX() - circ2.getCenterX(), 2) +
											Math.pow(circ1.getCenterY() - circ2.getCenterY(), 2));
		}
		//move circles
		private void moveCircles(Circle circ) {
			circ.setOnMouseDragged(e -> {
				double deg1 = (Math.PI / 2)-Math.atan2(e.getX()-mainCirc.getCenterX(),
																				e.getY()-mainCirc.getCenterY());
				System.out.println(deg1);
				circ.setCenterX(mainCirc.getCenterX()
					+ mainCirc.getRadius()*Math.cos(deg1));
				circ.setCenterY(mainCirc.getCenterY()
					+ mainCirc.getRadius()*Math.sin(deg1));
				refreshLabels();
			});
		}
		//refreshes the labels
		public void refreshLabels() {

			double len1 = length(outerCirc[0], outerCirc[1]);
			double len2 = length(outerCirc[1], outerCirc[2]);
			double len3 = length(outerCirc[2], outerCirc[0]);

			double deg1 = Math.toDegrees(Math.acos(((len2*len2)-(len1*len1)-(len3*len3)) / ((-2)*len1*len3)));
			labl[0].setText(String.format("%.2f", deg1));
			double deg2 = Math.toDegrees(Math.acos(((len3*len3)-(len2*len2)-(len1*len1)) / ((-2)*len1*len2)));
			labl[1].setText(String.format("%.2f", deg2));
			double deg3 = Math.toDegrees(Math.acos(((len1*len1)-(len2*len2)-(len3*len3)) / ((-2)*len2*len3)));
			labl[2].setText(String.format("%.2f", deg3));
		}
	}
}
