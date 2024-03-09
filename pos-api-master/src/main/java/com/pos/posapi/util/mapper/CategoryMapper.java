package com.pos.posapi.util.mapper;


import com.pos.posapi.dto.CategoryDto;
import com.pos.posapi.enity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto1);
    List<CategoryDto> toCategoryDtoList(List<Category> categories);
    CategoryDto toCategoryDto(Category category);
}
