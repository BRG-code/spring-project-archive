package org.zerock.service;

import org.zerock.domain.Isbn;

import java.util.List;

public interface IsbnService {
    List<Isbn> selectIsbn();
    int insertIsbn(Isbn isbn);
}
