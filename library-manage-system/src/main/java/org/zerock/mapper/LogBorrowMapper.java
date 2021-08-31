package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.LogBorrow;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogBorrowMapper {
    List<LogBorrow> selectLogBorrowByBookIdAndIsBorrowed(Map<String, Object> params);
    // 반납 처리
    int updateLogBorrowToReturned(long borrowId);
}
