package com.group03.loveit.models.post;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nhat
 */
public interface IPostDAO {

    CompletableFuture<PostDTO> getPostById(long id);
    public CompletableFuture<List<PostDTO>> getAllPosts();

    CompletableFuture<Void> insertPost(PostDTO post);

    CompletableFuture<Void> updatePost(PostDTO post);

    CompletableFuture<Void> deletePost(long id);
}
