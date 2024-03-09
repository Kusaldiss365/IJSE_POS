package com.pos.posapi.service.impl;


import com.pos.posapi.dto.CategoryDto;
import com.pos.posapi.dto.requestdto.RequestCategorySaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.enity.Category;
import com.pos.posapi.exception.EntryDuplicateException;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.repo.CategoryRepo;
import com.pos.posapi.service.CategoryService;
import com.pos.posapi.util.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CommonResponseDTO createCategory(RequestCategorySaveDto categoryDto) {
        String lastId = categoryRepo.findLastId("POS-CAT-", 9);

        String id = "POS-CAT-1";

        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split("POS-CAT-")[1])) + 1;
            id = "POS-CAT-" + i;
        }
        CategoryDto categoryDto1 = new CategoryDto(
                id,
                categoryDto.getCategory(),
                categoryDto.getDescription(),
                categoryDto.isActiveState()
        );
        if (!categoryRepo.existsById(categoryDto1.getCategoryId())){
            categoryRepo.save(categoryMapper.toCategory(categoryDto1));
        }else {
            throw new EntryDuplicateException("This Category Already Exists");
        }
        return new CommonResponseDTO(
                201,
                "CATEGORY CREATED SUCCESSFULLY",
                categoryDto1.getCategoryId(),
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO updateCategory(String id, RequestCategorySaveDto categoryDto) {
        Optional<Category> Category = categoryRepo.findById(id);
        if (Category.isEmpty()){
            throw new EntryNotFoundException("Category Not Exists");
        }

        Category selectedCategory = Category.get();
        selectedCategory.setCategory(categoryDto.getCategory());
        selectedCategory.setDescription(categoryDto.getDescription());
        selectedCategory.setActiveState(categoryDto.isActiveState());
        categoryRepo.save(selectedCategory);

        return new CommonResponseDTO(
                200,
                "CATEGORY UPDATED SUCCESSFULLY",
                selectedCategory.getCategoryId(),
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO deleteCategory(String id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isEmpty()){
            throw new EntryNotFoundException("Category Not Found");
        }
        categoryRepo.delete(category.get());
        return new CommonResponseDTO(
                204,
                "CATEGORY DELETED SUCCESSFULLY",
                category.get().getCategoryId(),
                new ArrayList<>()
        );
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        if (categories.isEmpty()) {
            throw new EntryNotFoundException("No Categories Found");
        }
        return categoryMapper.toCategoryDtoList(categories);
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        Category category = categoryRepo.getById(id);
        return categoryMapper.toCategoryDto(category);
    }
}
