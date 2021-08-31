package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.Member;
import com.cocoblue.securitytest.dto.MemberRole;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);
    private final SimpleJdbcInsert insertAction;
    private final SimpleJdbcInsert insertRoleAction;

    public MemberDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
        this.insertRoleAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member_role")
                .usingGeneratedKeyColumns("id");
    }

    public Member getMemberByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.queryForObject(MemberDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }

    public void insertUser(Member member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        long insertUserId = insertAction.executeAndReturnKey(params).longValue();
        SqlParameterSource roleParams = new BeanPropertySqlParameterSource(new MemberRole(insertUserId, "ROLE_USER"));
        insertRoleAction.execute(roleParams);
    }

    public void insertAdmin(Member member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        long insertUserId = insertAction.executeAndReturnKey(params).longValue();
        SqlParameterSource roleParams = new BeanPropertySqlParameterSource(new MemberRole(insertUserId, "ROLE_ADMIN"));
        insertRoleAction.execute(roleParams);
    }

    public Boolean checkEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<Map<String, Object>> queryResult = jdbc.queryForList(MemberDaoSqls.USER_COUNT_BY_EMAIL, map);

        return queryResult.isEmpty();
    }
}