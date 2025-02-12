package application;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class for managing questions to persist data across pages.
 */
public class QuestionsManager {
    private static QuestionsManager instance = null;
    private final List<Question> questions;

    private QuestionsManager() {
        this.questions = new ArrayList<>();
    }

    /**
     * Ensures only one instance of QuestionsManager exists.
     */
    public static QuestionsManager getInstance() {
        if (instance == null) {
            instance = new QuestionsManager();
        }
        return instance;
    }

    public void addQuestion(String title, String content) {
        int id = questions.size() + 1;
        Question newQuestion = new Question(id, title, content);
        questions.add(newQuestion);
        System.out.println("New Question Added: " + title);
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    public boolean deleteQuestion(int id) {
        return questions.removeIf(q -> q.getId() == id);
    }

    public boolean updateQuestion(int id, String newTitle, String newContent) {
        for (Question q : questions) {
            if (q.getId() == id) {
                q.setTitle(newTitle);
                q.setContent(newContent);
                return true;
            }
        }
        return false;
    }

    public List<Question> searchQuestions(String keyword) {
        List<Question> results = new ArrayList<>();
        for (Question q : questions) {
            if (q.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                q.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(q);
            }
        }
        return results;
    }
}
