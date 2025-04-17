import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class WhackAMoleGame extends Application {

    public static List<Button> buttonList = new ArrayList<>();
    public static int score = 0;
    private static double SUPER_MOLE_CHANCE;
    private Thread moleThread;
    private static final int GRID_SIZE = 7; 
    private static final int MOLE_RADIUS = 30;
    private boolean gameRunning = false;
    public Label livesLabel;
    private List<Hole> moleHoles = new ArrayList<>();
    public static Label scoreLabel;
    public int lives = 0;
    public Scene startScene;
    public Stage currentStage;
    public static boolean isImmune = false; // Immune from getting damage for one hit. 
    public static boolean superHeroStrength = false; // Doubles the amount of points you get, but also doubles damage...careful...
    public static Label gameMessage;
    public static int extraLifeCost = 30;
    public static int doubleScoreCost = 15;
    private static int lowerBound = 600; // Basically, the lower bound for the time that a mole is seen.
    private static int upperBound = 1500; // The upper bound for the time that a mole is seen. 
    private static String difficulty = "Medium";
    public static String instructionsPath = "instructions/";
    public static HBox extraLifeBox;
    public static HBox doubleScoreBox; 
    public static boolean canStart;
    public static Text scoreText;
    public static int dummyInteger;
    // Wrapper due to obvious reasons

    public static String readFileAsString(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readString(path);  
        } catch (IOException e) {
            e.printStackTrace();
            return null;  
        }
    }
    

    // Creating tabs

    public static VBox createTabContent(String content, Scene startingScene, Stage showingStage) {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER); 
        Text contentText = new Text(content);
        contentText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER); // Center text
        Button exitButton = new Button("Return to main menu.");
        exitButton.setOnAction(e -> {
            showingStage.setScene(startingScene);
        });
        exitButton.setStyle("-fx-alignment: center;");
        vbox.getChildren().addAll(contentText, exitButton);

        return vbox; 
    }

    // Makes difficulty easier or harder.

    private void manipulateDifficulty(int longestTime, int shortestTime, int extraLivesCost, int strengthCost, double superChance) {
        upperBound = longestTime;
        lowerBound = shortestTime;
        extraLifeCost = extraLivesCost;
        doubleScoreCost = strengthCost;
        SUPER_MOLE_CHANCE = superChance;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Whack-A-Mole");
        currentStage = primaryStage;

        // Start screen

        VBox startLayout = new VBox(20);
        startLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");
        Label title = new Label("Whack-A-Mole");
        title.setFont(Font.font(32));
        Button instructionsButton = new Button("Instructions");
        instructionsButton.setStyle("-fx-alignment: center; -fx-font-size: 16px;");
        Button difficultyStageButton = new Button("Start Game");
        difficultyStageButton.setStyle("-fx-font-size: 16px;");
        startLayout.getChildren().addAll(title, instructionsButton, difficultyStageButton);
        startScene = new Scene(startLayout, 600, 400);

        // Difficulty Screen:

        VBox difficultyLayout = new VBox(20);
        difficultyLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");
        Button button1 = new Button("Easy");
        Button button2 = new Button("Medium");
        Button button3 = new Button("Brutal");
        Button button4 = new Button("Impossible");

        button1.setPrefWidth(100); 
        button1.setPrefHeight(35);
        
        button2.setPrefWidth(100); 
        button2.setPrefHeight(35);
        
        button3.setPrefWidth(100); 
        button3.setPrefHeight(35);
        
        button4.setPrefWidth(100); 
        button4.setPrefHeight(35);
        Label difficultyMessage = new Label("Difficulty: Medium");

        Button startButton = new Button("Start Game");

        startButton.setPrefWidth(100); 
        startButton.setPrefHeight(50);
        button1.setOnAction(e -> {
            manipulateDifficulty(3000, 1000, 20, 10, 0.2);
            difficultyMessage.setText("Difficulty: Easy");
            difficulty = "Easy";
        }); 
        button2.setOnAction(e -> {
            manipulateDifficulty(1500, 600, 30, 15, 0.1);
            difficultyMessage.setText("Difficulty: Medium");
            difficulty = "Medium";
        });
        button3.setOnAction(e -> {
            manipulateDifficulty(750, 300, 50, 25, 0.05 );
            difficultyMessage.setText("Difficulty: Brutal");
            difficulty = "Brutal";
        });
        button4.setOnAction(e -> {
            manipulateDifficulty(400, 200, 100, 50, 0.05 );
            difficultyMessage.setText("Difficulty: Impossible");
            difficulty = "Impossible";    
        });
        difficultyLayout.getChildren().addAll(button1, button2, button3, button4, difficultyMessage, startButton);
        Scene difficultyScene = new Scene(difficultyLayout, 600, 400);

        // Game screen

        TabPane tabPane = new TabPane();

        Tab gameTab = new Tab("Game");
        gameTab.setClosable(false); // Disable closing of this tab

        // Game Screen
        BorderPane gameLayout = new BorderPane();
        scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(Font.font(20));
        livesLabel = new Label("Lives: " + lives);
        livesLabel.setFont(Font.font(20));
        gameMessage = new Label("Messages will pop up here.");
        gameMessage.setFont(Font.font(20));

        // Top bar, with messages
        HBox topBar = new HBox(20, scoreLabel, livesLabel, gameMessage);
        topBar.setStyle("-fx-padding: 10px; -fx-alignment: center;");
        gameLayout.setTop(topBar);

        // Setting up a grid: 
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-alignment: center;");

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Hole hole = new Hole(MOLE_RADIUS, this);
                moleHoles.add(hole);
                grid.add(hole, col, row);
            }
        }
        gameLayout.setCenter(grid);

        // End game button
        Button endGameButton = new Button("End Game");
        endGameButton.setOnAction(e -> showGameOverDialog(primaryStage));
        endGameButton.setStyle("-fx-alignment: center");
        gameLayout.setBottom(endGameButton);

        // Add game tab content
        gameTab.setContent(gameLayout);

        // Power-ups Tab
        Tab powerUpTab = new Tab("Power-ups");
        powerUpTab.setClosable(false); // Disable closing of this tab
        VBox powerUpLayout = showPowerUpInfoDialog();
        powerUpTab.setContent(powerUpLayout);

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(gameTab, powerUpTab);

        // Set up the scene
        Scene gameScene = new Scene(tabPane, 600, 400);

        // Instructions:

        TabPane instructions = new TabPane();

        Tab tab1 = new Tab();
        String tabOneContent = readFileAsString(instructionsPath+"start.txt");
        VBox vbox1 = createTabContent(tabOneContent, startScene, primaryStage);
        tab1.setText("Introduction");
        tab1.setClosable(false); // Disable closing of this tab
        tab1.setContent(vbox1);
    
        Tab tab2 = new Tab();
        tab2.setText("Basics");
        tab2.setClosable(false);
        String tabTwoContent = readFileAsString(instructionsPath+"start2.txt");
        VBox vbox2 = createTabContent(tabTwoContent, startScene, primaryStage);
        tab2.setContent(vbox2);

        Tab tab3 = new Tab();
        tab3.setText("Goal");
        tab3.setClosable(false);
        String tabThreeContent = readFileAsString(instructionsPath+"start3.txt");
        VBox vbox3 = createTabContent(tabThreeContent, startScene, primaryStage);
        tab3.setContent(vbox3);

        Tab tab4 = new Tab();
        tab4.setText("Usage");
        tab4.setClosable(false);
        String tabFourContent = readFileAsString(instructionsPath+"start4.txt");
        VBox vbox4 = createTabContent(tabFourContent, startScene, primaryStage);
        tab4.setContent(vbox4);

        Tab tab5 = new Tab();
        tab5.setText("Contact");
        tab5.setClosable(false);
        String tabFiveContent = readFileAsString(instructionsPath+"start5.txt");
        VBox vbox5 = createTabContent(tabFiveContent, startScene, primaryStage);
        tab5.setContent(vbox5);

        instructions.getTabs().addAll(tab1, tab2, tab3, tab4, tab5);
        Scene instructionsScene = new Scene(instructions, 600, 400);

        // Self Explanatory. 
        startButton.setOnAction(e -> {
            score = 0;
            lives = 3;
            scoreLabel.setText("Score: 0");
            livesLabel.setText("Lives: " + lives);
            primaryStage.setScene(gameScene);
            this.startMoleSpawning();
        });
        instructionsButton.setOnAction(e -> {
            primaryStage.setScene(instructionsScene);
        });
        difficultyStageButton.setOnAction(e -> {
            primaryStage.setScene(difficultyScene);
        });
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    private void startMoleSpawning() {
        gameRunning = true;

        // Threads to make sure that JavaFX does not collide.
        moleThread = new Thread(() -> {
            while (gameRunning) {
                try {
                    Random rand = new Random(); 
                    int sleepTime = rand.nextInt(upperBound - lowerBound) + lowerBound;                     Thread.sleep(sleepTime); // Wait for the specified time
                    // Update the UI with mole appearance
                    Platform.runLater(this::showRandomMole); 

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        moleThread.start(); 
    }

    private void stopMoleSpawning() {
        gameRunning = false;
    }

    private void showRandomMole() {
        for (Hole hole : moleHoles) {
            hole.hideMole();
        }
        Collections.shuffle(moleHoles);
        Hole moleHole = moleHoles.get(0);
        double chance = Math.random(); // Generates a number between 0.0 and 1.0
        if (chance < SUPER_MOLE_CHANCE) {
            moleHole.showSuperMole();
        } else {
            moleHole.showMole();
        }
    }

    // Game over dialog
    public void showGameOverDialog(Stage primaryStage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over!");
        alert.setContentText("Your score is: " + score + " on " + difficulty + " mode. Congrats!");
        stopMoleSpawning();
        gameMessage.setText("Messages will appear here.");
        superHeroStrength = false;
        isImmune = false;
        for (Button button : buttonList) {
            button.setDisable(false); 
        }
        ButtonType goBack = new ButtonType("Go back to start");
        alert.getButtonTypes().setAll(goBack);
        alert.showAndWait().ifPresent(response -> { 
            if (response == goBack) {
                primaryStage.setScene(startScene);
            }
        });
    }

    // Power-up info dialog
    public static VBox showPowerUpInfoDialog() {
        // Create a VBox to hold the content
        VBox vbox = new VBox(20);  // 20px spacing between elements
        vbox.setStyle("-fx-alignment: center; -fx-padding: 20px;");
    
        // Create a Text node to describe the power-ups
        Text powerUpText = new Text("Here are some power-ups");
        powerUpText.setStyle("-fx-font-size: 16px; -fx-text-alignment: center;");
        scoreText = new Text("Score: " + score);
        scoreText.setStyle("-fx-font-size: 12px; -fx-text-alignment: center;");
        Text noteText = new Text("Note: you can go in debt!");
        noteText.setStyle("-fx-font-size: 10px; -fx-text-alignment: center;");
        
        
        VBox powerUpShop = new VBox(10);
        powerUpShop.setStyle("-fx-alignment: center;");
        
        extraLifeBox = createPowerUpItem("Extra Life", "Shields you when you hit a non-colored hole.", extraLifeCost);
        doubleScoreBox = createPowerUpItem("Double Score", "Doubles your score (and lives lost)", doubleScoreCost);       

        powerUpShop.getChildren().addAll(extraLifeBox, doubleScoreBox);

        // Add the Text and Button to the VBox
        vbox.getChildren().addAll(powerUpText, scoreText, noteText, powerUpShop);
    
        return vbox; // Return the VBox containing the power-up info
    }

    private static HBox createPowerUpItem(String name, String description, int cost) {
        HBox powerUpItem = new HBox(10);
        powerUpItem.setStyle("-fx-alignment: center; -fx-padding: 10px;");
        
        Text nameText = new Text(name);
        nameText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Text descriptionText = new Text(description);
        descriptionText.setStyle("-fx-font-size: 10px; -fx-fill: gray;");
        
        Button costButton = new Button("Cost: " + Integer.toString(cost));
        buttonList.add(costButton);
        costButton.setStyle("-fx-font-size: 16px;");
        
        GridPane gridPane = new GridPane();
        gridPane.add(nameText, 0, 0);  
        gridPane.add(descriptionText, 1, 0);
        gridPane.add(costButton, 2, 0); 
        gridPane.setHgap(30);
        GridPane.setHalignment(nameText, HPos.CENTER);
        GridPane.setHalignment(descriptionText, HPos.CENTER);
        GridPane.setHalignment(costButton, HPos.CENTER); 

        // I would like to erase this from my memory.
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(50), event -> {
                if (name.equals("Extra Life")) {
                    costButton.setText("Cost: " + extraLifeCost); 
                } else if (name.equals("Double Score")) {
                    costButton.setText("Cost: " + doubleScoreCost);
                }
            })
        );   
        costButton.setOnAction(e -> {
            // No. 
            if (name.equals("Extra Life")) {
                isImmune = true;
                score -= extraLifeCost;
            } else if (name.equals("Double Score")) {
                superHeroStrength = true;
                score -= doubleScoreCost;
            }
            costButton.setDisable(true);
            costButton.setText("Sold!");
            scoreLabel.setText("Score: " + score);
            scoreText.setText("Score: " + score);        
        });
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        powerUpItem.getChildren().addAll(gridPane);
    
        powerUpItem.setStyle("-fx-border-color: lightgray; -fx-border-width: 2px; -fx-background-color: #f0f0f0;");
        powerUpItem.setPadding(new Insets(10));
        return powerUpItem;
    }  
   

    public static void main(String[] args) {
        launch(args);
    }
}
