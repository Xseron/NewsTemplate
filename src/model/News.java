package model;

import java.sql.Timestamp;

public class News {
    private int id;
    private Timestamp postDate;
    private int categoryId;
    private String title;
    private String content;

    public News(int id, Timestamp postDate, int categoryId, String title, String content) {
        this.id = id;
        this.postDate = postDate;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public News(){}

    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}