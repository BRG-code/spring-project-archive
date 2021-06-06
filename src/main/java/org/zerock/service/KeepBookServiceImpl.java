package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.KeepBook;
import org.zerock.mapper.KeepBookMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Log4j
public class KeepBookServiceImpl implements KeepBookService {
    private final KeepBookMapper keepBookMapper;

    @Override
    public List<KeepBook> selectKeepBook() {
        return keepBookMapper.selectKeepBook();
    }

    @Override
    public KeepBook selectKeepBookById(Long id) {
        return keepBookMapper.selectKeepBookById(id);
    }

    @Override
    public List<KeepBook> selectKeepBookWithPaging(Criteria cri) {
        Map<String, Object> params = new HashMap<>();

        int from = (cri.getPageNum() - 1) * cri.getAmount();
        int to = cri.getPageNum() * cri.getAmount();

        params.put("from", from);
        params.put("to", to);
        params.put("typeArr", cri.getTypeArr());
        params.put("keyword", cri.getKeyword());

        return keepBookMapper.selectKeepBookWithPaging(params);
    }

    @Override
    public int getTotalCount() {
        return keepBookMapper.getTotalCount();
    }
}
