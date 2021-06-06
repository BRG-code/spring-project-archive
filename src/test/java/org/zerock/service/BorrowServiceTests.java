package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Borrow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class BorrowServiceTests {
    @Autowired
    private BorrowService borrowService;

    @Test
    public void judgeAlreadyBorrowedTest() {
        /*
            이미 대출 중인 사람의 ID를 넣고 체크
         */
        Borrow borrow = new Borrow();
        borrow.setPerson(2);
        assertTrue(borrowService.judgeAlreadyBorrowed(borrow));
    }

    @Test
    @Transactional
    public void insertBorrowTest() {
        /*
            대출 수행 테스트
         */
        Borrow borrow = new Borrow();
        borrow.setPerson(2);
        borrow.setBookId(7);
        assertEquals(1, borrowService.insertBorrow(borrow));
    }
}
