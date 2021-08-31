package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class CommentDaoTest {
    @Autowired
    CommentDao commentDao;

    @Test
    public void getCommentTest() {
        System.out.println(commentDao.getComment("3"));
    }
}
