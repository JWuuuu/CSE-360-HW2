package application;

import databasePart1.DatabaseHelper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.User; // Import User class

/**
 * AdminHomePage displays the home screen for admin users.
 */
public class AdminHomePage {
    private QuestionsManager questionsManager = QuestionsManager.getInstance(); // Ensure persistence

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label adminLabel = new Label("Hello, Admin!");
        adminLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Manage Users Button
        Button manageUsersButton = new Button("Manage Users");
        manageUsersButton.setOnAction(event -> new ManageUsersPage().show(primaryStage));

        // View Activity Logs Button
        Button activityLogsButton = new Button("View Activity Logs");
        activityLogsButton.setOnAction(event -> new ActivityLogsPage().show(primaryStage));

        // Manage Questions Button
        Button manageQuestionsButton = new Button("Manage Questions");
        manageQuestionsButton.setOnAction(event -> new ManageQuestionsPage().show(primaryStage));

        // Ask Question Button
        Button askQuestionButton = new Button("Ask a Question");
        askQuestionButton.setOnAction(event -> new AskQuestionPage().show(primaryStage));

        // **Fixed Role Selection Button**
        Button roleSelectionButton = new Button("Change Role");
        roleSelectionButton.setOnAction(event -> {
            DatabaseHelper dbHelper = new DatabaseHelper();
            User currentUser = new User();  // ✅ Fix: Create a new user instance
            new RoleSelectionPage(dbHelper, dbHelper.getUserRoles("admin")).show(primaryStage, currentUser);
        });

        // Logout Button
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(event -> new StartCSE360().start(primaryStage));

        layout.getChildren().addAll(adminLabel, manageUsersButton, activityLogsButton, 
                                    manageQuestionsButton, askQuestionButton, roleSelectionButton, logoutButton);

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Home Page");
        primaryStage.show();
    }
}
