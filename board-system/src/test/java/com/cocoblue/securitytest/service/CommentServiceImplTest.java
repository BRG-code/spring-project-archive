package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class CommentServiceImplTest {
    @Autowired
    CommentServiceImpl commentServiceImpl;

    @Test
    public void getCommentCountTest() {
        System.out.println(commentServiceImpl.getCommentCount("2"));
    }
}
