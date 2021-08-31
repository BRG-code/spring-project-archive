package org.zerock.service;

import org.zerock.domain.Criteria;
import org.zerock.domain.KeepBook;

import java.util.List;

public interface KeepBookService {
    List<KeepBook> selectKeepBook();
    KeepBook selectKeepBookById(Long id);
    List<KeepBook> selectKeepBookWithPaging(Criteria cri);
    int getTotalCount();
}
