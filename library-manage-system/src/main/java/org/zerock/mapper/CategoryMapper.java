package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategory();
}
