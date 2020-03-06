/*
Assignment 1 part 2
@author Adam Green, 100653971
*/

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class part2 extends Application {

	public void start(Stage primaryStage) {

		//setting gridpane
		GridPane gridPane = new GridPane();
		gridPane.setVgap(15);
		gridPane.setHgap(15);
		gridPane.setPadding(new Insets(20));
		//textfield labels
		TextField investmentAmount = new TextField();
		TextField futureVal = new TextField();
		futureVal.setEditable(false);

		TextField numOfYears = new TextField();
		TextField annualInterestRate = new TextField();
		//adding labels to gridpane
		gridPane.add(investmentAmount,1,0);
		gridPane.add(new Label("Investement amount: "),0,0);
		gridPane.add(numOfYears,1,1);
		gridPane.add(new Label("Number of years: "),0,1);
		gridPane.add(annualInterestRate,1,2);
		gridPane.add(new Label("Annual interes rate: "),0,2);
		gridPane.add(futureVal,1,3);
		gridPane.add(new Label("Future value: "),0,3);
		//adding calc button
		Button bCalc = new Button("Calculate!");
		bCalc.setOnAction(e -> {
    double investAmount = Double.parseDouble(investmentAmount.getText());
    double annualIntRate = Double.parseDouble(annualInterestRate.getText());
    double numYears = Double.parseDouble(numOfYears.getText());
		//future value calculation
    futureVal.setText(Double.toString(investAmount *
        Math.pow((1 + 1. / 12 / 100 * annualIntRate), 12 * numYears)));
    });

		gridPane.add(bCalc,1,4);
		GridPane.setHalignment(bCalc, HPos.RIGHT);

		//Setting alignments
		investmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
		numOfYears.setAlignment(Pos.BOTTOM_RIGHT);
		annualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
		futureVal.setAlignment(Pos.BOTTOM_RIGHT);
		//setting scene
		Scene scene = new Scene(gridPane);
		//display
		primaryStage.setTitle("Part2");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
