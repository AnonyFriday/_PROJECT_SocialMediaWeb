package com.group03.loveit.models.comment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nhat
 */
public interface ICommentDAO {
    public CompletableFuture<CommentDTO> getCommentById(long id);
    public CompletableFuture<List<CommentDTO>> getCommentsByPost(long postId);
    public CompletableFuture<List<CommentDTO>> getChildComments(long id);
    public CompletableFuture<Void> insertComment(CommentDTO comment);
    public CompletableFuture<Void> updateComment(CommentDTO comment);
    public CompletableFuture<Void> deleteComment(long id);
}
