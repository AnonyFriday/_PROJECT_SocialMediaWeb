package com.group03.loveit.models.comment;

import java.time.LocalDateTime;
import com.group03.loveit.models.post.PostDTO;
import com.group03.loveit.models.user.UserDTO;

/**
 * @author Nhat
 */
public class CommentDTO {
    private long id;
    private PostDTO post;
    private UserDTO user;
    private String content;
    private LocalDateTime createdAt;
    private String status;
    private CommentDTO reply;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public CommentDTO getReply() {
        return reply;
    }

    public void setReply(CommentDTO reply) {
        this.reply = reply;
    }

    // Constructors
    public CommentDTO(long id) {
        this.id = id;
    }

    public CommentDTO(long id, PostDTO post, UserDTO user, String content, LocalDateTime createdAt, String status, CommentDTO reply) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.status = status;
        this.reply = reply;
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
        final CommentDTO other = (CommentDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentDTO{" + "id=" + id + ", post=" + post + ", user=" + user + ", content=" + content + ", createdAt=" + createdAt + ", status=" + status + ", reply=" + reply + '}';
    }
}
