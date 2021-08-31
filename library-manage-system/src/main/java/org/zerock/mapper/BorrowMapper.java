package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Borrow;

import java.util.List;

@Mapper
public interface BorrowMapper {
    List<Borrow> selectBorrowByPersonAndBorrowed(Borrow borrow);
    int insertBorrow(Borrow borrow);
}
