package application;

import java.util.ArrayList;
import java.util.List;

/**
 * QuestionsManager handles storing and retrieving questions and answers.
 * Implements Singleton pattern to persist data throughout the application.
 */
public class QuestionsManager {
    private static QuestionsManager instance;
    private final List<Question> questions; // Stores all questions
    private final List<Answer> answers;     // Stores all answers

    private QuestionsManager() {
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public static QuestionsManager getInstance() {
        if (instance == null) {
            instance = new QuestionsManager();
        }
        return instance;
    }

    /**
     * Adds a new question.
     */
    public void addQuestion(String title, String content) {
        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            System.out.println("Error: Question must have a title and content.");
            return;
        }
        Question newQuestion = new Question(questions.size() + 1, title, content);
        questions.add(newQuestion);
        System.out.println("Question Added: " + title);
    }

    /**
     * Deletes a question based on its ID.
     */
    public void deleteQuestion(int questionId) {
        questions.removeIf(q -> q.getId() == questionId);
        System.out.println("Question with ID " + questionId + " has been deleted.");
    }

    /**
     * Updates the title of a question based on its ID.
     */
    public void updateQuestionTitle(int questionId, String newTitle) {
        for (Question q : questions) {
            if (q.getId() == questionId) {
                q.setTitle(newTitle);
                System.out.println("Question ID " + questionId + " title updated to: " + newTitle);
                return;
            }
        }
        System.out.println("Question ID " + questionId + " not found.");
    }

    /**
     * Adds an answer to a specific question.
     */
    public void addAnswer(int questionId, String answerText) {
        if (answerText == null || answerText.isEmpty()) {
            System.out.println("Error: Answer cannot be empty.");
            return;
        }
        Answer newAnswer = new Answer(answers.size() + 1, questionId, answerText);
        answers.add(newAnswer);
        System.out.println("Answer Added to Question ID " + questionId + ": " + answerText);
    }

    /**
     * Searches for questions based on a keyword.
     */
    public List<Question> searchQuestions(String keyword) {
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question q : questions) {
            if (q.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                q.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                filteredQuestions.add(q);
            }
        }
        return filteredQuestions;
    }

    /**
     * Retrieves all stored questions.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Retrieves all answers for a specific question.
     */
    public List<Answer> getAnswersForQuestion(int questionId) {
        List<Answer> filteredAnswers = new ArrayList<>();
        for (Answer a : answers) {
            if (a.getQuestionId() == questionId) {
                filteredAnswers.add(a);
            }
        }
        return filteredAnswers;
    }
}
