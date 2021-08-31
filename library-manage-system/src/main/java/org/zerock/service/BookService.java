package org.zerock.service;

import org.zerock.domain.Book;

import java.util.List;

public interface BookService {
    int insertBook(List<Book> book);
}
