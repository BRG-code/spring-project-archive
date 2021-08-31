package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Comment;

import java.util.List;

public interface CommentService {
    public long getCommentCount(String postId);
    public List<Comment> getComments(String postId);
    public Boolean writeComment(Comment comment);
    public Boolean deleteComment(String id);
    public Comment getComment(String commentId);
}
