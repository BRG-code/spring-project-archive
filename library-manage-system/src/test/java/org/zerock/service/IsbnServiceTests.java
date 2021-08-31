package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Isbn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class IsbnServiceTests {
    @Autowired
    private IsbnService isbnService;

    @Test
    public void selectIsbnTest() {
        assertNotNull(isbnService.selectIsbn());
    }

    @Test
    @Transactional
    public void insertIsbn() {
        Isbn isbn = new Isbn();
        isbn.setIsbn(123456);
        isbn.setCategory(0);
        isbn.setLocation(1);
        isbn.setName("테스트");
        isbn.setWriter("테스트");

        assertEquals(1, isbnService.insertIsbn(isbn));
    }
}
