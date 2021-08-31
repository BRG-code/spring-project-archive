package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.LogBorrow;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class LogBorrowServiceTests {
    @Autowired
    private LogBorrowService logBorrowService;

    @Test
    public void selectLogBorrowByBookIdAndIsBorrowedTest() {
        List<LogBorrow> logBorrows = logBorrowService.selectLogBorrowByBookIdAndIsBorrowed(3, false);
        assertNotNull(logBorrows);
    }

    @Test
    @Transactional
    public void updateLogBorrowToReturnedTest() {
        assertTrue(logBorrowService.updateLogBorrowToReturned(3));
    }
}
