package com.cocoblue.securitytest.service;

import java.util.List;

import com.cocoblue.securitytest.dto.Comment;
import com.cocoblue.securitytest.dto.Post;
import com.cocoblue.securitytest.dao.PostDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    @Transactional
    public List<Post> getPostsAll(String boardName) {
        return postDao.getPostsAll(boardName);
    }

    @Override
    public Post getPost(String id) {
        return postDao.getPost(id);
    }

    @Override
    public List<Comment> getComments(String post_id) {
        return null;
    }

    @Override
    public Boolean writePost(Post post) {
        return postDao.writePost(post);
    }

    @Override
    public void increaseViewNum(String post_id) {
        postDao.increaseViewNum(post_id);
    }

    @Override
    public List<Post> getPostsByPage(String boardName, int page) {
        return postDao.getPostsByPage(boardName, page);
    }

    @Override
    public long getPostsCount(String boardName) {
        return postDao.getPostsCount(boardName);
    }

    @Override
    public List<Post> getPostsByKeyword(String boardName, String keyword, int page) {
        return postDao.getPostsByKeyword(boardName, "%" + keyword + "%", page);
    }

    @Override
    public long getPostsCountByKeyword(String boardName, String keyword) {
        return postDao.getPostsCountByKeyword(boardName, "%" + keyword + "%");
    }

    @Override
    public Boolean deletePost(String postId) {
        return postDao.deletePost(postId);
    }

    @Override
    public Boolean updatePost(Post post) {
        return postDao.updatePost(post);
    }
}
