package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.KeepBook;

import java.util.List;
import java.util.Map;

@Mapper
public interface KeepBookMapper {
    List<KeepBook> selectKeepBook();
    KeepBook selectKeepBookById(Long id);
    List<KeepBook> selectKeepBookWithPaging(Map<String, Object> params);
    int getTotalCount();
}
