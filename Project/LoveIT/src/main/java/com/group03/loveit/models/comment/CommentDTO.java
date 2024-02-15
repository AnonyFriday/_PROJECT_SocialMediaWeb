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
    private long postId;
    private long userId;
    private String content;
    private LocalDateTime createdAt;
    private String status;
    private long replyId;

    // ===========================
    // == Getters and Setters
    // ===========================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
    }

    // ===========================
    // == Constructors
    // ===========================
    public CommentDTO(long id) {
        this.id = id;
    }

    public CommentDTO(long id, long postId, long userId, String content, LocalDateTime createdAt, String status, long replyId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.status = status;
        this.replyId = replyId;
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
        return "CommentDTO{" + "id=" + id + ", postId=" + postId + ", userId=" + userId + ", content=" + content + ", createdAt=" + createdAt + ", status=" + status + ", replyId=" + replyId + '}';
    }
}
