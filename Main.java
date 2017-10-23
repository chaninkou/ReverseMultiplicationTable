package application;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Main extends Application {

	private final IntegerProperty answerNumber = new SimpleIntegerProperty();

	@Override
	public void start(Stage primaryStage) {
		// Creating the border pane
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(getTable());

		// Creating a h box for label, text field
		HBox mainSystem = new HBox(16);
		mainSystem.setPadding(new Insets(15, 10, 15, 10));
		mainSystem.setAlignment(Pos.CENTER);

		// add css
		mainSystem.getStyleClass().add("box2");

		Label question = new Label("Enter Answer: ");
		mainSystem.getChildren().add(question);

		TextField answerValue = new TextField();
		mainSystem.getChildren().add(answerValue);

		// Button
		Button clickAnswer = new Button("Click to find answer");
		mainSystem.getChildren().add(clickAnswer);

		// Using event handler
		clickAnswer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				answerNumber.set(Integer.parseInt(answerValue.getText()));
			}
		});

		// Setting where the question and table at
		mainPane.setBottom(mainSystem);
		mainPane.setCenter(thePane());

		Scene sceneGraph = new Scene(mainPane, 650, 710);
		sceneGraph.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// Setting the scene
		primaryStage.setScene(sceneGraph);

		// Setting the title
		primaryStage.setTitle("LAB 8");

		// displaying the whole thing
		primaryStage.show();
	}

	// creating a grid pane
	public GridPane thePane() {
		// creating a grid pane
		GridPane border = new GridPane();

		// making a label
		Label[][] display = new Label[11][11];

		for (int row = 0; row < 11; row++) {
			for (int col = 0; col < 11; col++) {
				// creating a label
				Label a = new Label();
				setUpLabel(a, col, row);
				display[row][col] = a;
				// adding the column and row
				border.add(a, col, row);
			}
		}
		return border;
	}
	// end method

	// creating a text field in order to get the correct factors
	public int showText(TextField solve) {
		// calling the class
		CalculatorForFactor calculation = new CalculatorForFactor();

		// create a value to get the next int
		int value = Integer.parseInt(solve.getText());

		// making a list in order to find the factor
		List<Integer> answer = calculation.findFactor(value);

		for (int k = 0; k < answer.size() - 1;) {
			return answer.get(k);
		}
		return 0;
	}
	// end method

	// creating a method for h box
	private HBox getTable() {
		// Naming the table
		HBox tableBox = new HBox();
		tableBox.setPadding(new Insets(30, 30, 30, 30));

		// Getting from css
		tableBox.getStyleClass().add("box1");
		tableBox.setAlignment(Pos.TOP_CENTER);

		// Naming the entire thing
		Label name = new Label("Table Of Reverse Multiplication");
		tableBox.getChildren().add(name);

		return tableBox;
	}
	// end method

	// method to find add to show the answer and reset them
	public void setUpLabel(final Label pane, final int column, final int row) {
		answerNumber.addListener((obs, oldValue, newValue) -> {
			if (column * row == newValue.intValue()) {
				pane.getStyleClass().add("gridAnswer");
			} else {
				pane.getStyleClass().remove("gridAnswer");
			}
		});

		// creating two string for number on the left and right
		String leftNumber = String.valueOf(row);
		String rightNumber = String.valueOf(column);

		// set it to 200 and 100 so it would look perfect when we zoom in
		pane.setPrefWidth(200);
		pane.setPrefHeight(100);

		// set the width to 1 so it would be close
		pane.setStyle("-fx-stroke-border: black; -fx-border-width: 1;");
		pane.setAlignment(Pos.CENTER);

		// making a if statement if the row and column is zero
		if (row == 0 || column == 0) {
			pane.getStyleClass().add("gridBorder");
			if (row == 0)
				pane.setText(rightNumber);
			else if (column == 0)
				pane.setText(leftNumber);
		} else {
			pane.setText(leftNumber + " * " + rightNumber);
			pane.getStyleClass().add("gridInsideBox");
		}
	}
	// end method

	public static void main(String[] args) {
		launch(args);
	}
}
