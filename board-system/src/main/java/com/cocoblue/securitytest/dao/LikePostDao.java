package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.LikePost;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LikePostDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final SimpleJdbcInsert insertAction;

    public LikePostDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("like_post")
                .usingGeneratedKeyColumns("id");
    }

    public long getLikeCount(long postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        long result = 0;

        try {
            result = (long) jdbc.queryForMap(LikePostDaoSqls.SELECT_LIKE_COUNT_BY_POST_ID, map).get("count");
        } catch (Exception e) {
            // 게시글에 추천이 없으면, null이 반환되서 에러가 발생하므로, 0으로 설정한 것을 그대로 유지
        }

        return result;
    }

    public boolean judgeAlreadyLike(long postId, long memberId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("memberId", memberId);

        long result;

        try {
            result = (long) jdbc.queryForMap(LikePostDaoSqls.SELECT_LIKE_COUNT_BY_POST_ID_AND_MEMBER_ID, map).get("count");
        } catch (Exception e) {
            e.printStackTrace();
            // 데이터가 없을 경우 추천한 적이 없는 것이므로 true 반환
            return false;
        }

        return result > 0;
    }

    public boolean insertLikePost(@NotNull LikePost likePost) {
        likePost.setLikeTime(LocalDateTime.now());
        SqlParameterSource params = new BeanPropertySqlParameterSource(likePost);

        // 제대로 INSERT가 되면 true
        return insertAction.execute(params) > 0;
    }
}
