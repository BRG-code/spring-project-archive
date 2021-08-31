package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Isbn;

import java.util.List;

@Mapper
public interface IsbnMapper {
    List<Isbn> selectIsbn();
    int insertIsbn(Isbn isbn);
}
