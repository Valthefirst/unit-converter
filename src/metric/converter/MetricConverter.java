/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metric.converter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author nneji
 */
public class MetricConverter extends Application {

    private static TextField kilotextField;
    private static Label resultLabel;
    private static RadioButton milesButton;
    private static RadioButton feetButton;
    private static RadioButton inchesButton;

    @Override
    public void start(Stage primaryStage) {

        Label promptLabel = new Label("Enter a distance in kilometers:");

        kilotextField = new TextField();

        //Create the radioButton controls
        milesButton = new RadioButton("Convert to Miles");
        feetButton = new RadioButton("Convert to Feet");
        inchesButton = new RadioButton("Convert to Inches");

        //Select the milesButton control
        milesButton.setSelected(true);

        // Add the RadioButton controls to a ToggleGroup
        ToggleGroup radioGroup = new ToggleGroup();
        milesButton.setToggleGroup(radioGroup);
        feetButton.setToggleGroup(radioGroup);
        inchesButton.setToggleGroup(radioGroup);

        //Create Button to perform convertion        
        Button btn = new Button();
        btn.setText("Convert");

        btn.setOnAction(new btnHandler());

        resultLabel = new Label();

        HBox promptHBox = new HBox(10, promptLabel, kilotextField);

        HBox radioHBox = new HBox(20, milesButton, feetButton, inchesButton);

        VBox mainVbox = new VBox(10, promptHBox, radioHBox, btn, resultLabel);

        mainVbox.setAlignment(Pos.CENTER);

        mainVbox.setPadding(new Insets(10));

        Scene scene = new Scene(mainVbox);

        primaryStage.setTitle("Metric Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    static class btnHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            // Constants for the convertion factors
            final double MILES_CONVERSION = 0.6214;
            final double FEET_CONVERSION = 3281.0;
            final double INCHES_CONVERSION = 39370.0;

            //Variable to hold the result
            double result = 0;

            //Get the kilometers
            double kilometers = Double.parseDouble(kilotextField.getText());

            //Perform the selected conversion
            if (milesButton.isSelected()) {
                result = kilometers * MILES_CONVERSION;
            }
            
            if (feetButton.isSelected()) {
                result = kilometers * FEET_CONVERSION;
            }
            
            if (inchesButton.isSelected()) {
                result = kilometers * INCHES_CONVERSION;
            }
            
            //Display the results
            resultLabel.setText(String.format("%,.2f", result));
        }
    }

}
