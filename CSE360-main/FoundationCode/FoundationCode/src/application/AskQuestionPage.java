package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * AskQuestionPage displays a simple form for the user to ask a question.
 */
public class AskQuestionPage {

    /**
     * Displays the Ask Question page in the provided primary stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */
    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        
        // Title Label
        Label titleLabel = new Label("Ask a Question");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Automated Test: Verify Title
        System.out.println("Test: Verify Title Label - Expected: 'Ask a Question', Actual: " + titleLabel.getText());

        // Text area for entering a question
        TextArea questionArea = new TextArea();
        questionArea.setPromptText("Type your question here...");
        questionArea.setPrefWidth(400);
        questionArea.setPrefHeight(200);
        
        // Automated Test: Verify Question Area
        System.out.println("Test: Verify Question Area - Expected Prompt: 'Type your question here...', Actual: " + questionArea.getPromptText());
        
        // Submit Button
        Button submitButton = new Button("Submit Question");
        submitButton.setOnAction(event -> {
            String questionText = questionArea.getText();
            
            // Automated Test: Verify Non-Empty Question Submission
            if (questionText.isEmpty()) {
                System.out.println("Test: Question Submission - Failed (Question is empty)");
            } else {
                System.out.println("Test: Question Submission - Success (Question Submitted: " + questionText + ")");
            }
            
            // Display confirmation alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Question Submitted");
            alert.setHeaderText(null);
            alert.setContentText("Your question has been submitted.");
            alert.showAndWait();
        });
        
        // Back Button to return to UserHomePage
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            System.out.println("Test: Back Button Clicked - Navigating to UserHomePage");
            // Navigate back to UserHomePage
            new UserHomePage().show(primaryStage);
        });
        
        layout.getChildren().addAll(titleLabel, questionArea, submitButton, backButton);
        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ask a Question");
        
        // Automated Test: Scene Loaded
        System.out.println("Test: AskQuestionPage Loaded - Scene Title: " + primaryStage.getTitle());
        
        primaryStage.show();
    }
}
