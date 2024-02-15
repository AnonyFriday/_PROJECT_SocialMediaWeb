package com.group03.loveit.models.comment;

/**
 * @author Nhat
 */
public interface ICommentDAO {
    public CommentDTO getCommentById(long id);
    public void insertComment(CommentDTO comment);
    public void updateComment(CommentDTO comment);
    public void deleteComment(long id);
}
