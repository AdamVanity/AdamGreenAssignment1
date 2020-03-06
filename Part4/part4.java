/*
Assignment 1 part 4
@author Adam Green, 100653971
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class part4 extends Application {

	public void start(Stage primaryStage) {
		//creating histogram
		Histogram letterHG = new Histogram();
		TextField txtFile = new TextField("letters.txt");
		//adding display items
		txtFile.setPrefColumnCount(30);
		txtFile.setOnAction(e -> letterHG.setCounts(txtFile.getText()));
		Button buttonViewFile = new Button("View File");
		buttonViewFile.setOnAction(e -> letterHG.setCounts(txtFile.getText()));
		Label lablFile = new Label("Filename: ", txtFile);
		lablFile.setContentDisplay(ContentDisplay.RIGHT);
		HBox botBox = new HBox(5);
		botBox.getChildren().addAll(txtFile, lablFile, buttonViewFile);
		//main border pane
		BorderPane mainBPane = new BorderPane();
		mainBPane.setCenter(letterHG);
		mainBPane.setBottom(botBox);

		//display
		Scene scene = new Scene(mainBPane);
		primaryStage.setTitle("Part4");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public class Histogram extends Pane {

		private double[] counts = new double[26];
		public Histogram() {
			//dimensions
			setPrefWidth(800);
			setPrefHeight(300);
		}

		public void setCounts(String fileName) {

			try (Scanner fileInput = new Scanner(new File(fileName))) {
				while (fileInput.hasNext()) {
					String line = fileInput.next();
					for (int i = 0; i < line.length(); i++) {
						char lett = Character.toUpperCase(line.charAt(i));
						if (lett >= 'A' && lett <= 'Z') {
							counts[lett - 'A']++;
						}
					}
				}
				reDrawHisto();
			} catch(FileNotFoundException ex) { }


		}
		//normalize
		private void countAll() {
			double sum = 0;
			int countLength = counts.length;
			for(int i = 0; i < countLength; i++)
				sum += counts[i];
			for(int i = 0; i < countLength; i++)
				counts[i] /= sum;
		}

		private void reDrawHisto() {
			double scale = 5;
			int a = 10;
			getChildren().clear();
			countAll();
			int countLength = counts.length;

			for (int i = 0; i < countLength; i++) {
				Rectangle hisBar = new Rectangle(20, scale*getHeight()*counts[i]);
				hisBar.setY(getHeight()-hisBar.getHeight()-30);
				hisBar.setX(a);
				hisBar.setStroke(Color.BLACK);
				hisBar.setFill(Color.WHITE);
				Text fileTxt = new Text(a+hisBar.getWidth()/2-3,
						hisBar.getY()+hisBar.getHeight()+20,
						Character.toString((char)(i+'A')));
				getChildren().addAll(hisBar, fileTxt);
				a += 30;
			}
		}
	}
}
