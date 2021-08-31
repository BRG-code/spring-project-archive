package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class MemberDaoTest {
    @Autowired
    MemberDao memberDao;

}
