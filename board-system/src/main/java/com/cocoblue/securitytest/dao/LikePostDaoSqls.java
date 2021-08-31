package com.cocoblue.securitytest.dao;

public class LikePostDaoSqls {
    public static String SELECT_LIKE_COUNT_BY_POST_ID = "SELECT COUNT(*) as count FROM like_post WHERE post_id = :postId";
    public static String SELECT_LIKE_COUNT_BY_POST_ID_AND_MEMBER_ID = "SELECT COUNT(*) as count FROM like_post " +
            "WHERE post_id = :postId AND member_id = :memberId";
}
