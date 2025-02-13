package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;

/**
 * UserHomePage displays the home screen for a regular user.
 */
public class UserHomePage {
    private QuestionsManager questionsManager = QuestionsManager.getInstance();
    private DatabaseHelper databaseHelper = new DatabaseHelper(); // Fixed initialization

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label welcomeLabel = new Label("Welcome, User!");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Button to Ask a Question
        Button askQuestionButton = new Button("Ask a Question");
        askQuestionButton.setOnAction(event -> {
            System.out.println("Navigating to AskQuestionPage...");
            new AskQuestionPage().show(primaryStage);
        });

        // Button to Change Role
        Button roleSelectionButton = new Button("Change Role");
        roleSelectionButton.setOnAction(event -> {
            System.out.println("Navigating to RoleSelectionPage...");
            new RoleSelectionPage(databaseHelper, databaseHelper.getUserRoles("defaultUser"))
                .show(primaryStage, new User("defaultUser", "", "user"));
        });

        // 🔥 **Back Button to Admin Home Page**
        Button backButton = new Button("Back to Admin Page");
        backButton.setOnAction(event -> {
            System.out.println("Navigating back to AdminHomePage...");
            new AdminHomePage().show(primaryStage);
        });

        // Logout Button
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(event -> {
            System.out.println("Logging out...");
            new StartCSE360().start(primaryStage);
        });

        layout.getChildren().addAll(welcomeLabel, askQuestionButton, roleSelectionButton, backButton, logoutButton);
        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Home Page");
        primaryStage.show();
    }
}
