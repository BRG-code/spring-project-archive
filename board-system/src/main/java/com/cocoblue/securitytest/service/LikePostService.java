package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.LikePost;

public interface LikePostService {
    long getLikeCount(long postId);
    boolean judgeAlreadyLike(long postId, long memberId);
    boolean insertLikePost(LikePost likePost);
}
