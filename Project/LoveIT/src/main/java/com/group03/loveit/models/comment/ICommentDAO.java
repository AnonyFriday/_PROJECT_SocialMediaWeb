package com.group03.loveit.models.comment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nhat
 */
public interface ICommentDAO {
    CompletableFuture<CommentDTO> getCommentById(long id);
    CompletableFuture<List<CommentDTO>> getCommentsByPost(long postId);
    CompletableFuture<CommentDTO> getTopCommentByPost(long postId);
    CompletableFuture<List<CommentDTO>> getRepliesByComment(long id);
    CompletableFuture<Void> insertComment(CommentDTO comment);
    CompletableFuture<Void> updateComment(CommentDTO comment);
    CompletableFuture<Void> deleteComment(long id);
}
