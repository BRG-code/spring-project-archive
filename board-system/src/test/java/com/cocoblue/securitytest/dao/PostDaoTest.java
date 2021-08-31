package com.cocoblue.securitytest.dao;

import com.cocoblue.securitytest.config.ApplicationConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class PostDaoTest {
    @Autowired
    PostDao postDao;
}
