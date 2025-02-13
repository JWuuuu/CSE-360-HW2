package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

/**
 * AskQuestionPage allows users to ask and answer questions.
 */
public class AskQuestionPage {
    private QuestionsManager questionsManager = QuestionsManager.getInstance();
    private ListView<String> questionListView;
    private ObservableList<String> questionList = FXCollections.observableArrayList();

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label titleLabel = new Label("Ask or Answer Questions");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField questionTitleField = new TextField();
        questionTitleField.setPromptText("Enter question title");

        TextArea questionContentField = new TextArea();
        questionContentField.setPromptText("Enter question content");

        Button submitQuestionButton = new Button("Submit Question");
        submitQuestionButton.setOnAction(event -> {
            String title = questionTitleField.getText().trim();
            String content = questionContentField.getText().trim();
            if (title.isEmpty() || content.isEmpty()) {
                showAlert("Error", "Question must have a title and content.");
                return;
            }
            questionsManager.addQuestion(title, content);
            updateQuestionList();
            questionTitleField.clear();
            questionContentField.clear();
            showAlert("Success", "Question submitted successfully.");
        });

        Label questionListLabel = new Label("Select a question to answer:");
        questionListView = new ListView<>(questionList);
        updateQuestionList();

        TextField answerField = new TextField();
        answerField.setPromptText("Enter your answer");

        Button submitAnswerButton = new Button("Submit Answer");
        submitAnswerButton.setOnAction(event -> {
            String selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
            if (selectedQuestion == null) {
                showAlert("Error", "Please select a question to answer.");
                return;
            }

            int questionId = Integer.parseInt(selectedQuestion.split(": ")[0]);
            String answerText = answerField.getText().trim();
            if (answerText.isEmpty()) {
                showAlert("Error", "Answer cannot be empty.");
                return;
            }

            questionsManager.addAnswer(questionId, answerText);
            answerField.clear();
            showAlert("Success", "Answer submitted successfully.");
        });

        Button viewAnswersButton = new Button("View Answers");
        viewAnswersButton.setOnAction(event -> {
            String selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
            if (selectedQuestion == null) {
                showAlert("Error", "Please select a question to view answers.");
                return;
            }

            int questionId = Integer.parseInt(selectedQuestion.split(": ")[0]);
            List<Answer> answers = questionsManager.getAnswersForQuestion(questionId);

            if (answers.isEmpty()) {
                showAlert("Info", "No answers available for this question.");
            } else {
                showAnswersPopup(answers);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> new UserHomePage().show(primaryStage));

        layout.getChildren().addAll(titleLabel, questionTitleField, questionContentField, submitQuestionButton,
                questionListLabel, questionListView, answerField, submitAnswerButton, viewAnswersButton, backButton);

        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ask a Question");
        primaryStage.show();
    }

    private void updateQuestionList() {
        questionList.clear();
        for (Question q : questionsManager.getQuestions()) {
            questionList.add(q.getId() + ": " + q.getTitle());
        }
        questionListView.setItems(questionList);
    }

    private void showAnswersPopup(List<Answer> answers) {
        Stage answerStage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label titleLabel = new Label("Answers for the Selected Question");
        ListView<String> answerListView = new ListView<>();
        ObservableList<String> answerList = FXCollections.observableArrayList();

        for (Answer a : answers) {
            answerList.add("Answer ID " + a.getId() + ": " + a.getContent());
        }
        answerListView.setItems(answerList);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> answerStage.close());

        layout.getChildren().addAll(titleLabel, answerListView, closeButton);
        Scene scene = new Scene(layout, 400, 300);
        answerStage.setScene(scene);
        answerStage.setTitle("Answers");
        answerStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
