package com.group03.loveit.models.comment;

import java.time.LocalDateTime;

/**
 * @author Nhat
 */
public class CommentDTO {
    // ===========================
    // == Fields
    // ===========================
    private long id;
    private long userId;
    private String content;
    private LocalDateTime createdAt;
    private int heartTotal;
    private String status;
    private String imageUrl;

    // ===========================
    // == Getters and Setters
    // ===========================
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

    // ===========================
    // == Constructors
    // ===========================
    public CommentDTO(long id) {
        this.id = id;
    }

    public CommentDTO(long id, long userId, String content, LocalDateTime createdAt, int heartTotal, String status, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.heartTotal = heartTotal;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    // ===========================
    // == Override Methods
    // ===========================
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
        final CommentDTO other = (CommentDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentDTO{" + "id=" + id + ", userId=" + userId + ", content=" + content + ", createdAt=" + createdAt + ", heartTotal=" + heartTotal + ", status=" + status + ", imageUrl=" + imageUrl + '}';
    }
}
