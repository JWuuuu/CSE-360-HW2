package application;

/**
 * Represents an answer linked to a specific question.
 */
public class Answer {
    private int id;
    private int questionId;
    private String content;

    public Answer(int id, int questionId, String content) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
    }

    public int getId() { return id; }
    public int getQuestionId() { return questionId; }
    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
