package application;

/**
 * Represents a question with an ID, title, and content.
 */
public class Question {
    private int id;
    private String title;
    private String content;

    public Question(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
