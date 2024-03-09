package com.pos.posapi.dto;

import com.pos.posapi.enity.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryDto {
    private String categoryId;
    private CategoryType category;
    private String description;
    private boolean activeState;

    public CategoryDto(CategoryType category, String description, boolean activeState) {
        this.category = category;
        this.description = description;
        this.activeState = activeState;
    }
}
