package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Post;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Post> rowMapper = BeanPropertyRowMapper.newInstance(Post.class);
    private final SimpleJdbcInsert insertAction;

    public PostDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("post")
                .usingGeneratedKeyColumns("id");
    }

    public List<Post> getPostsAll(String boardName) {
        Map<String, Object> map = new HashMap<>();
        map.put("boardName", boardName);

        return jdbc.query(PostDaoSqls.SELECT_ALL_BY_BOARD_NAME, map, rowMapper);
    }

    public List<Post> getPostsByPage(String boardName, int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("boardName", boardName);
        map.put("start", page * 4);
        map.put("end", page * 4 + 4);

        return jdbc.query(PostDaoSqls.SELECT_POSTS_BY_PAGE, map, rowMapper);
    }

    public Post getPost(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        return jdbc.queryForObject(PostDaoSqls.SELECT_BY_POST_ID, map, rowMapper);
    }

    public Boolean writePost(Post post) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        return insertAction.execute(params) > 0;
    }

    public void increaseViewNum(String postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        jdbc.update(PostDaoSqls.UPDATE_VIEW_NUMBER, map);
    }

    public long getPostsCount(String boardName) {
        Map<String, Object> map = new HashMap<>();
        map.put("boardName", boardName);

        return (long) jdbc.queryForMap(PostDaoSqls.SELECT_COUNT_BY_BOARD_NAME, map).get("post_count");
    }

    public List<Post> getPostsByKeyword(String boardName, String keyword, int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("boardName", boardName);
        map.put("keyword", keyword);
        map.put("start", page * 4);
        map.put("end", page * 4 + 4);

        return jdbc.query(PostDaoSqls.SELECT_POSTS_BY_KEYWORD, map, rowMapper);
    }

    public long getPostsCountByKeyword(String boardName, String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("boardName", boardName);
        map.put("keyword", keyword);

        try {
            return (long) jdbc.queryForMap(PostDaoSqls.SELECT_COUNT_BY_KEYWORD, map).get("post_count");
        } catch (Exception e) {
            return 0;
        }
    }

    public Boolean deletePost(String postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);

        return jdbc.update(PostDaoSqls.DELETE_BY_POST_ID, map) > 0;
    }

    public Boolean updatePost(Post post) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", post.getId());
        map.put("title", post.getTitle());
        map.put("content", post.getContent());

        return jdbc.update(PostDaoSqls.UPDATE_BY_POST_ID, map) > 0;
    }
}
