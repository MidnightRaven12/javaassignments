import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;

public class TicTacToe extends Application {

public final int noCells = 6;
  private char whoseTurn = 'X';

  private Cell[][] cell =  new Cell[noCells][noCells];

  // Create and initialize a status label
  private Label lblStatus = new Label("X's turn to play");

  @Override // Override the start method in the Application class
  
  public void start(Stage primaryStage) {

    // Pane to hold cell
    GridPane pane = new GridPane(); 
    for (int i = 0; i < noCells; i++)
      for (int j = 0; j < noCells; j++)
        pane.add(cell[i][j] = new Cell(), j, i);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(lblStatus);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 100*noCells, 100*noCells);
    primaryStage.setTitle("TicTacToe"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage   
  }

  /** Determine if the cell are all occupied */
  public boolean isFull() {
    for (int i = 0; i < noCells; i++)
      for (int j = 0; j < noCells; j++)
        if (cell[i][j].getToken() == ' ')
          return false;

    return true;
  }

  /** Determine if the player with the specified token wins */
  public boolean isWon(char token) {
    for (int i = 0; i < noCells; i++) {
        for (int j = 0; j < noCells; j++) {
            if (cell[i][j].getToken() != token) {
                break;
            } else if (j == noCells - 1) {
                return true;
            }
        }
    }
    for (int j = 0; j < noCells; j++) {
        for (int i = 0; i < noCells; i++) {
            if (cell[i][j].getToken() != token) {
                break;
            } else if (i == noCells - 1) {
                return true;
            }
        }
    }

    for (int i = 0; i < noCells; i++) {
        if (cell[i][i].getToken() != token) {
            break;
        } else if (i == noCells - 1) {
            return true;
        }
    }
    for (int i = 0; i < noCells; i++) {
        if (cell[i][noCells-1-i].getToken() != token) {
            break;
        } else if (i == noCells - 1) {
            return true;
        }
    }

    return false;
  }

  // An inner class for a cell
  public class Cell extends Pane {
    // Token used for this cell
    private char token = ' ';

    public Cell() {
      setStyle("-fx-border-color: black"); 
      this.setPrefSize(800, 800);
      this.setOnMouseClicked(e -> handleMouseClick());
    }

    /** Return token */

    
    public char getToken() {
      return token;
    }

    public void setToken(char c) {
      token = c;
      
      if (token == 'X') {
        Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
        line1.endXProperty().bind(this.widthProperty().subtract(10));
        line1.endYProperty().bind(this.heightProperty().subtract(10));
        Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
        line2.startYProperty().bind(this.heightProperty().subtract(10));
        line2.endXProperty().bind(this.widthProperty().subtract(10));
        
        // Add the lines to the pane
        this.getChildren().addAll(line1, line2); 
      }
      else if (token == 'O') {
        Ellipse ellipse = new Ellipse(this.getWidth() / 2, 
          this.getHeight() / 2, this.getWidth() / 2 - 10, 
          this.getHeight() / 2 - 10);
        ellipse.centerXProperty().bind(
          this.widthProperty().divide(2));
        ellipse.centerYProperty().bind(
            this.heightProperty().divide(2));
        ellipse.radiusXProperty().bind(
            this.widthProperty().divide(2).subtract(10));        
        ellipse.radiusYProperty().bind(
            this.heightProperty().divide(2).subtract(10));   
        ellipse.setStroke(Color.BLACK);
        ellipse.setFill(Color.WHITE);
        
        getChildren().add(ellipse); // Add the ellipse to the pane
      }
    }
    
    
    private void handleMouseClick() {
      // If cell is empty and game is not over
      if (token == ' ' && whoseTurn != ' ') {
        setToken(whoseTurn); // Set token in the cell

        // Check game status
        if (isWon(whoseTurn)) {
          lblStatus.setText(whoseTurn + " won! The game is over");
          whoseTurn = ' '; // Game is over
        }
        else if (isFull()) {
          lblStatus.setText("Draw! The game is over");
          whoseTurn = ' '; // Game is over
        }
        else {
          // Change the turn
          whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
          // Display whose turn
          lblStatus.setText(whoseTurn + "'s turn");
        }
      }
    }
  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
