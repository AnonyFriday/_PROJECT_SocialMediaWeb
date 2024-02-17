package com.group03.loveit.models.comment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nhat
 */
public interface ICommentDAO {
    public CommentDTO getCommentById(long id);
    public CompletableFuture<List<CommentDTO>> getCommentsByPost(long postId);
    public CompletableFuture<List<CommentDTO>> getChildComments(long id);
    public void insertComment(CommentDTO comment);
    public void updateComment(CommentDTO comment);
    public void deleteComment(long id);
}
