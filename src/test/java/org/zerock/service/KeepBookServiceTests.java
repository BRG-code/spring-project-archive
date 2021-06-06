package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.KeepBook;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
@Log4j
public class KeepBookServiceTests {
    @Autowired
    private KeepBookService keepBookService;

    @Test
    public void selectKeepBookTestByNoArgsCri() {
        /*
            criteria에 값이 없을 때 제대로 갖고오는지 테스트
         */
        Criteria criteria = new Criteria();
        assertNotNull(keepBookService.selectKeepBookWithPaging(criteria));
    }

    @Test
    public void selectKeepBookTestByArgsCri() {
        /*
            criteria에 값이 있을 때 제대로 갖고 오는지 테스트
         */
        Criteria criteria = new Criteria();
        criteria.setPageNum(2);
        assertNotNull(keepBookService.selectKeepBookWithPaging(criteria));
    }

    @Test
    public void selectKeepBookTestById() {
        /*
            모든 책 리스트를 받고, 각각이 제대로 받아오는지 테스트
         */
        List<KeepBook> keepBooks = keepBookService.selectKeepBook();

        for(KeepBook keepBook : keepBooks) {
            assertEquals(keepBook, keepBookService.selectKeepBookById(keepBook.getId()));
        }
    }

    @Test
    public void getTotalCountTest() {
        /*
            모든 책 리스트의 수와 Total Count가 일치하는지 테스트
         */
        int countFromList = keepBookService.selectKeepBook().size();
        assertEquals(countFromList, keepBookService.getTotalCount());
    }
}
