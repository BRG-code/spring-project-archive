package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.LikePostDao;
import com.cocoblue.securitytest.dto.LikePost;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class LikePostServiceImpl implements LikePostService {
    private final LikePostDao likePostDao;

    public LikePostServiceImpl(LikePostDao likePostDao) {
        this.likePostDao = likePostDao;
    }

    @Override
    public long getLikeCount(long postId) {
        return likePostDao.getLikeCount(postId);
    }

    @Override
    public boolean judgeAlreadyLike(long postId, long memberId) {
        return likePostDao.judgeAlreadyLike(postId, memberId);
    }

    @Override
    public boolean insertLikePost(@NotNull LikePost likePost) {
        return likePostDao.insertLikePost(likePost);
    }
}
