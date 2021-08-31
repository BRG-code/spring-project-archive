package org.zerock.service;

import org.zerock.domain.LogBorrow;

import java.util.List;

public interface LogBorrowService {
    List<LogBorrow> selectLogBorrowByBookIdAndIsBorrowed(long bookId, Boolean isBorrowed);
    // 반납 처리
    boolean updateLogBorrowToReturned(long borrowId);
}
