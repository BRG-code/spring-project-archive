package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Comment;
import com.cocoblue.securitytest.dto.Post;

import java.util.List;

public interface PostService {
    public List<Post> getPostsAll(String boardName);
    public Post getPost(String id);
    public List<Comment> getComments(String post_id);
    public Boolean writePost(Post post);
    public void increaseViewNum(String post_id);
    public List<Post> getPostsByPage(String boardName, int page);
    public long getPostsCount(String boardName);
    public List<Post> getPostsByKeyword(String boardName, String keyword, int page);
    public long getPostsCountByKeyword(String boardName, String keyword);
    public Boolean deletePost(String postId);
    public Boolean updatePost(Post post);
}