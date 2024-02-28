package model;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private String commentText;
    private Timestamp postDate;
    private int userId;
    private int newsId;

    public Comment(int id, String commentText, Timestamp postDate, int userId, int newsId) {
        this.id = id;
        this.commentText = commentText;
        this.postDate = postDate;
        this.userId = userId;
        this.newsId = newsId;
    }

    public Comment() {}

    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}