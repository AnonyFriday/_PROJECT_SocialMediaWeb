package com.group03.loveit.models.post;

import java.time.LocalDateTime;

/**
 * @author Nhat
 */
public class PostDTO {
    private long id;
    private long userId;
    private String content;
    private LocalDateTime createdAt;
    private int heartTotal;
    private int commentTotal;
    private String status;
    private String imageUrl;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getHeartTotal() {
        return heartTotal;
    }

    public void setHeartTotal(int heartTotal) {
        this.heartTotal = heartTotal;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Constructors
    public PostDTO(long id) {
        this.id = id;
    }

    public PostDTO(long id, long userId, String content, LocalDateTime createdAt, int heartTotal, int commentTotal, String status, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.heartTotal = heartTotal;
        this.commentTotal = commentTotal;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    // Override Methods
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PostDTO other = (PostDTO) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostDTO{" + "id=" + id + ", userId=" + userId + ", content=" + content + ", createdAt=" + createdAt + ", heartTotal=" + heartTotal + ", commentTotal=" + commentTotal + ", status=" + status + ", imageUrl=" + imageUrl + '}';
    }
}