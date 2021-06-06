package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.service.MemberService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class MemberControllerTests {
    @Autowired
    private MemberController memberController;

    @Autowired
    private MemberService memberService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    @WithAnonymousUser
    public void getLoginPageTestByAnonymous() throws Exception {
        /*
            비로그인 상태에서 로그인 페이지로 넘어가지는지 테스트
         */
        mockMvc.perform(get("/member/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @Transactional
    public void insertTest() throws Exception {
        /*
            회원 가입이 제대로 되는지 테스트
         */

        mockMvc.perform(MockMvcRequestBuilders
                .post("/member/register")
                .param("name", "테스트")
                .param("password", "123456")
                .param("email",generateNotRegisteredId()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    @Transactional
    public void emailCheckTest() throws Exception {
        /*
            이메일 중복 체크가 제대로 되는지 테스트
         */

        mockMvc.perform(MockMvcRequestBuilders
                .post("/member/emailCheck")
                .param("email",generateNotRegisteredId()))
                .andExpect(status().isOk());
    }

    // 없는 아이디를 생성하여, 테스트가 예외 없이 성공적으로 될 수 있도록 함.
    private String generateNotRegisteredId() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10) + "@test.com";

        if(!memberService.checkEmail(generatedString)) {
            generatedString = generateNotRegisteredId();
        }

        return generatedString;
    }
}
