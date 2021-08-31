package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.dto.MemberRole;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRoleDao {
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<MemberRole> rowMapper = BeanPropertyRowMapper.newInstance(MemberRole.class);

    public MemberRoleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<MemberRole> getRolesByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.query(MemberRoleDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }
}
