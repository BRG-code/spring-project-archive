package org.zerock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.Isbn;
import org.zerock.mapper.IsbnMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class IsbnServiceImpl implements IsbnService {
    private final IsbnMapper isbnMapper;

    @Override
    public List<Isbn> selectIsbn() {
        return isbnMapper.selectIsbn();
    }

    @Override
    public int insertIsbn(Isbn isbn) {
        return isbnMapper.insertIsbn(isbn);
    }
}
