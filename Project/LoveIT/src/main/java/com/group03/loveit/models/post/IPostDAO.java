package com.group03.loveit.models.post;

/**
 * @author Nhat
 */
public interface IPostDAO {
    public PostDTO getPostById(long id);
    public void insertPost(PostDTO post);
    public void updatePost(PostDTO post);
    public void deletePost(long id);
}
