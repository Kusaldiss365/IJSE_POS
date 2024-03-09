package com.pos.posapi.dto.requestdto;


import com.pos.posapi.enity.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestCategorySaveDto {
    private CategoryType category;
    private String description;
    private boolean activeState;
}
