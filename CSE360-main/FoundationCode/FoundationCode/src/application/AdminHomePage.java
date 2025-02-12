package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * AdminHomePage displays the home screen for admin users.
 */
public class AdminHomePage {
    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label adminLabel = new Label("Hello, Admin!");
        adminLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button manageUsersButton = new Button("Manage Users");
        manageUsersButton.setOnAction(event -> new ManageUsersPage().show(primaryStage));

        Button activityLogsButton = new Button("View Activity Logs");
        activityLogsButton.setOnAction(event -> new ActivityLogsPage().show(primaryStage));

        Button manageQuestionsButton = new Button("Manage Questions");
        manageQuestionsButton.setOnAction(event -> new ManageQuestionsPage().show(primaryStage));

        Button askQuestionButton = new Button("Ask a Question");
        askQuestionButton.setOnAction(event -> new AskQuestionPage().show(primaryStage));

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(event -> new StartCSE360().start(primaryStage));

        layout.getChildren().addAll(adminLabel, manageUsersButton, activityLogsButton, manageQuestionsButton, askQuestionButton, logoutButton);
        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Page");
        primaryStage.show();
    }
}