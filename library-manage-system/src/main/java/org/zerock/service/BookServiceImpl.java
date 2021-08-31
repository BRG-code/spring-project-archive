package org.zerock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.Book;
import org.zerock.mapper.BookMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;

    @Override
    public int insertBook(List<Book> books) {
        return bookMapper.insertBook(books);
    }
}
