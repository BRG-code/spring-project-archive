package com.cocoblue.securitytest.dao;

public class MemberDaoSqls {
    public static final String SELECT_ALL_BY_EMAIL = "SELECT id, name, password, email, create_date, modify_date FROM member WHERE email = :email";
    public static final String USER_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM member WHERE email = :email GROUP BY email";
}