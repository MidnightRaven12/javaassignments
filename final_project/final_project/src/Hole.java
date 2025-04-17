import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Hole extends Circle {

    public WhackAMoleGame game;
    private boolean isMoleVisible = false;

    // Constructor to create a Hole (circle)
    public Hole(double radius, WhackAMoleGame app) {
        super(radius, Color.GRAY); // Default color is gray to represent an empty hole
        this.game = app;
        // Set mouse click event handler for this Hole
        setOnMouseClicked(this::handleClick);
    }

    // Show the mole (red circle) in this hole
    public void showMole() {
        setFill(Color.RED);
        isMoleVisible = true;
    }

    public void showSuperMole() {
        setFill(Color.YELLOW);
        isMoleVisible = true;
    }

    // Hide the mole (reset color to gray)
    public void hideMole() {
        setFill(Color.GRAY);
        isMoleVisible = false;
    }

    // Handle points based on mole type
    private void handlePoints(int points, WhackAMoleGame game, boolean notVisible) {
        
        int multiplier = 1;
        if (game.superHeroStrength) {
            multiplier = 2;
        }
        // Basically, making sure that you don't lose points when your shield is hit.
        if (!(this.game.isImmune && points < 0)) {
            this.game.score += points*multiplier;            
        }
        this.game.scoreLabel.setText("Score: " + this.game.score);
        if (notVisible && this.game.isImmune) {
            this.game.gameMessage.setText("Your shield has been broken!");
            this.game.isImmune = false;
        } else if (notVisible && !this.game.isImmune) {
            this.game.lives -= multiplier;
        } 
        this.game.livesLabel.setText("Lives: " + this.game.lives);
        if (game.lives <= 0) {
            game.showGameOverDialog(game.currentStage);
        }
        hideMole(); // Hide mole after being clicked
    }

    // Handle the click on the hole (mole)
    private void handleClick(MouseEvent event) {
        if (isMoleVisible && this.getFill() == Color.RED) {
            handlePoints(1,this.game, false);  
        } else if (isMoleVisible) {
            handlePoints(5,this.game, false);  
        } else {
            handlePoints(-1, this.game, true); 
        }
    }

    // Check if a mole is currently visible in the hole
    public boolean isMoleVisible() {
        return isMoleVisible;
    }
}
