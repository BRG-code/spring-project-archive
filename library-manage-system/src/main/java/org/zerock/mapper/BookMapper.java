package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    int insertBook(List<Book> book);
}
