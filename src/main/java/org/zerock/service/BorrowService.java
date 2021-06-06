package org.zerock.service;

import org.zerock.domain.Borrow;

public interface BorrowService {
    boolean judgeAlreadyBorrowed(Borrow borrow);
    int insertBorrow(Borrow borrow);
}
