package org.zerock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class BookControllerTests {
    @Autowired
    private BookController bookController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getBookListFirstPageTest() throws Exception {
        /*
            첫 페이지가 제대로 받아와지는지 테스트
         */
        mockMvc.perform(get("/book/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("list"));
    }

    @Test
    public void getBookListSecondPageTest() throws Exception {
        /*
            두번째 페이지가 제대로 받아와지는지 테스트
         */
        mockMvc.perform(get("/book/list")
                .param("pageNum", String.valueOf(2))
                .param("amount", String.valueOf(10)))
                .andExpect(status().isOk());
    }

    @Test
    public void getBookTest() throws Exception {
        mockMvc.perform(get("/book/get/3"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"));
    }

    @Test
    @Transactional
    @WithMockUser(roles = "ADMIN")
    public void postRegisterTest() throws Exception {
        Object randomObj = new Object() {
            public final String isbn = "123456";
            public final String name = "테스트";
            public final String writer = "테스트";
            public final String category = "0";
            public final String location = "1";
            public final String count = "1";
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mockMvc.perform(MockMvcRequestBuilders
        .post("/book/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getRegisterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/register"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(status().isOk());
    }
}
