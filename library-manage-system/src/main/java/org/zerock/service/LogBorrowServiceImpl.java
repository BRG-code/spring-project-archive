package org.zerock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.LogBorrow;
import org.zerock.mapper.LogBorrowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LogBorrowServiceImpl implements LogBorrowService {
    private final LogBorrowMapper logBorrowMapper;

    @Override
    public List<LogBorrow> selectLogBorrowByBookIdAndIsBorrowed(long bookId, Boolean isBorrowed) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("isBorrowed", isBorrowed);

        return logBorrowMapper.selectLogBorrowByBookIdAndIsBorrowed(params);
    }

    @Override
    public boolean updateLogBorrowToReturned(long borrowId) {
        // 반영된 행이 1개라면, 제대로 반영 된 것임.
        return logBorrowMapper.updateLogBorrowToReturned(borrowId) == 1;
    }
}
