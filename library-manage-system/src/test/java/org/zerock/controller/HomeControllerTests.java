package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class HomeControllerTests {
    @Autowired
    private HomeController homeController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    @WithAnonymousUser
    public void homeRedirectTest() throws Exception {
        /*
            루트로 접속하면 책 리스트로 넘어가는지 테스트
         */
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/book/list"));
    }

    @WithAnonymousUser
    @Test
    public void loginRedirectTest() throws Exception {
        /*
            /login 을 요청하면 제대로 리다이렉트 되는지 테스트
         */
        mockMvc.perform(get("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/member/login"));
    }
}
