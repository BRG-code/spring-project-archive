package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Book;
import org.zerock.domain.Isbn;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class BookServiceTests {
    @Autowired
    private BookService bookService;

    @Autowired
    private IsbnService isbnService;

    @Test
    // Transactional 어노테이션을 추가하여 수행한 작업이 취소되게 함.
    @Transactional
    public void insertBookTest() {
        /*
            등록되어 있는 책 정보들을 모두 한 번씩 추가해봄.
         */
        List<Isbn> isbns = isbnService.selectIsbn();
        List<Book> books = new ArrayList<>();

        for(Isbn isbn : isbns) {
            Book book = new Book();
            book.setIsbn(isbn.getIsbn());

            books.add(book);
        }

        assertEquals(isbns.size(), bookService.insertBook(books));
    }
}
