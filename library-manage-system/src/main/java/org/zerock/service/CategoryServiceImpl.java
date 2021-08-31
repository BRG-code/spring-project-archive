package org.zerock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.Category;
import org.zerock.mapper.CategoryMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategory() {
        return categoryMapper.selectCategory();
    }
}
