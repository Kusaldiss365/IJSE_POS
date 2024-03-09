package com.pos.posapi.service;

import com.pos.posapi.dto.CategoryDto;
import com.pos.posapi.dto.requestdto.RequestCategorySaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;


import java.util.List;

public interface CategoryService {
    CommonResponseDTO createCategory(RequestCategorySaveDto categoryDto);

    CommonResponseDTO updateCategory(String id, RequestCategorySaveDto categoryDto);

    CommonResponseDTO deleteCategory(String id);

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(String id);
}
