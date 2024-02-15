package com.group03.loveit.models.favourite;

/**
 * @author Nhat
 */
public class FavouriteDTO {
    // ===========================
    // == Fields
    // ===========================
    private long postId;
    private long userId;

    // ===========================
    // == Getters and Setters
    // ===========================
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

    // ===========================
    // == Constructor
    // ===========================
    public FavouriteDTO(long postId, long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    // ===========================
    // == Override Methods
    // ===========================
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (postId ^ (postId >>> 32));
        result = prime * result + (int) (userId ^ (userId >>> 32));
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
        final FavouriteDTO other = (FavouriteDTO) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FavouriteDTO{" + "postId=" + postId + ", userId=" + userId + '}';
    }
}