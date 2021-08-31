package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.CommentDao;
import com.cocoblue.securitytest.dto.Comment;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public long getCommentCount(String postId) {
        return commentDao.getCommentCount(postId);
    }

    @Override
    public List<Comment> getComments(String postId) {
        return commentDao.getComments(postId);
    }

    @Override
    public Boolean writeComment(Comment comment) {
        return commentDao.writeComment(comment);
    }

    @Override
    public Boolean deleteComment(String id) {
        return commentDao.deleteComment(id);
    }

    @Override
    public Comment getComment(String commentId) {
        return commentDao.getComment(commentId);
    }
}
