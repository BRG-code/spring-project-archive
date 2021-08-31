package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.zerock.domain.Borrow;
import org.zerock.mapper.BorrowMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j
public class BorrowServiceImpl implements BorrowService {
    private final BorrowMapper borrowMapper;

    @Override
    public boolean judgeAlreadyBorrowed(Borrow borrow) {
        log.info(borrow);
        borrow.setBorrowed(true);
        List<Borrow> borrows = borrowMapper.selectBorrowByPersonAndBorrowed(borrow);
        log.info(borrows);

        // 현재 유효한 대출 기록이 존재하면, True (대출 불가능)
        return !borrows.isEmpty();
    }

    @Override
    public int insertBorrow(Borrow borrow) {
        return borrowMapper.insertBorrow(borrow);
    }
}
