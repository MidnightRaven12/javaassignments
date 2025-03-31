import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class ConverterApp extends Application {
  
  private static TextField convertFrom = new TextField();
  private static Label convertTo = new Label("");
  private static Button btCalculate = new Button("Convert");
  private static ComboBox<String> conversionBox = new ComboBox<>();
  private static ComboBox<String> direction = new ComboBox<>();
  private static VBox vbox = new VBox(20); // UI organization thing.
  private static Label instructions = new Label("Put in your conversion value, and then output it.");
  private static Label error = new Label("");
  @Override 
  public void start(Stage primaryStage) {
    Scene scene = new Scene(vbox, 400, 250);
    GridPane gridPane = new GridPane();
    conversionBox.getItems().addAll("Miles and Kilometers", "Grams and Pounds", "Liters and Gallons", "Degrees and Radians");
    direction.getItems().addAll("To","From");
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(new Label("Convert From:"), 0, 0);
    gridPane.add(convertFrom, 1, 0);
    gridPane.add(new Label("Convert To:"), 0, 1);
    gridPane.add(convertTo, 1, 1);
    gridPane.add(new Label("Conversion Direction:"), 0, 2);
    gridPane.add(direction, 1, 2);
    gridPane.add(new Label("Conversion Units:"), 0, 3);
    gridPane.add(conversionBox, 1, 3);
    gridPane.add(btCalculate, 1, 4);
    vbox.getChildren().addAll(instructions, gridPane, error);

    gridPane.setAlignment(Pos.CENTER);
    convertFrom.setAlignment(Pos.BOTTOM_RIGHT);
    convertFrom.setAlignment(Pos.BOTTOM_RIGHT);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);
    
    
    // Process events
    btCalculate.setOnAction(e -> {
      try {
        convertTo.setText(String.valueOf(ConverterApp.convert())); 
      }
      catch (IllegalArgumentException except) {
        error.setText("You have entered an invalid value to convert from.");
      }
      catch (Exception expt) {
        error.setText("Unknown error");
      }});

    // Create a scene and place it in the stage
    primaryStage.setTitle("LoanCalculator"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  public static double convert() throws Exception { // The direction is for completeness.
    int input;
    
    double value = Double.parseDouble(convertFrom.getText());
    double reverse;
    if (direction.getValue().equals("To")) {
      reverse = 0;
    } else if (direction.getValue().equals("From")) {
      reverse = 1;
    } else {
      throw new IllegalArgumentException("what");
    }
    if (conversionBox.getValue().equals("Miles and Kilometers")) {
      input = 1;
    } else if (conversionBox.getValue().equals("Grams and Pounds")) {
      input = 2;
    } else if (conversionBox.getValue().equals("Liters and Gallons")) {
      input = 3;
    } else if (conversionBox.getValue().equals("Degrees and Radians")) {
      input = 4;
    } else {
      input = -1;
    }
    if (value < 0) {
      throw new IllegalArgumentException("Invalid input for the value.");
    } else if (input < 1 || input > 4) {
      throw new IllegalArgumentException("what (convert function made some number from 1-4 equal to some number not in between 1 and 4.)");
    }
    switch (input) {
      case 1:
        return value*Math.pow(1.609344,Math.pow(-1.0, reverse));				
      case 2: 
        return value*Math.pow(0.002204623,Math.pow(-1.0, reverse)); 
      case 3:		
        return value*Math.pow(0.264172,Math.pow(-1.0, reverse)); 
      case 4:
        return value*Math.pow(Math.PI/180,Math.pow(-1.0, reverse)); 
      default:
        return -1.0;								
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}