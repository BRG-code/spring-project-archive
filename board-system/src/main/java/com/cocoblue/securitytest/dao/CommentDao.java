package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
    private final SimpleJdbcInsert insertAction;

    public CommentDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("comment")
                .usingGeneratedKeyColumns("id");
    }

    public long getCommentCount(String postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        long result = 0;

        try {
            result = (long) jdbc.queryForMap(CommentDaoSqls.SELECT_COUNT_BY_POST_ID, map).get("comment_count");
        } catch (Exception e) {
            // 게시글에 댓글이 없으면, null이 반환되서 에러가 발생하므로, 0으로 설정한 것을 그대로 유지
        }

        return result;
    }

    public List<Comment> getComments(String postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        return jdbc.query(CommentDaoSqls.SELECT_ALL_BY_POST_ID, map, rowMapper);
    }

    public Comment getComment(String commentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("commentId", commentId);

        return jdbc.queryForObject(CommentDaoSqls.SELECT_BY_COMMENT_ID, map, rowMapper);
    }

    public Boolean writeComment(Comment comment) {
        comment.setWriteTime(LocalDateTime.now());
        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);

        // 제대로 INSERT가 되면 true
        return insertAction.execute(params) > 0;
    }

    public Boolean deleteComment(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("commentId", id);

        return jdbc.update(CommentDaoSqls.DELETE_BY_COMMENT_ID, map) > 0;
    }
}
