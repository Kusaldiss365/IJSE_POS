package com.pos.posapi.controller;

import com.pos.posapi.dto.CartItemDto;
import com.pos.posapi.dto.CategoryDto;
import com.pos.posapi.dto.requestdto.RequestCategorySaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.service.CategoryService;
import com.pos.posapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping()
    public ResponseEntity<StandardResponse> create(@RequestBody RequestCategorySaveDto categoryDto){
        CommonResponseDTO responseDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseDto.getCode(),
                        responseDto.getMessage(),
                        responseDto.getData()
                ), HttpStatus.CREATED
        );
    }
    @PutMapping(params = {"id"})
    public ResponseEntity<StandardResponse> update(
            @RequestParam (value = "id") String id,
            @RequestBody RequestCategorySaveDto categoryDto){
        CommonResponseDTO responseDto = categoryService.updateCategory(id,categoryDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseDto.getCode(),
                        responseDto.getMessage(),
                        responseDto.getData()
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<StandardResponse> delete(
            @RequestParam (value = "id") String id){
        CommonResponseDTO responseDto = categoryService.deleteCategory(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseDto.getCode(),
                        responseDto.getMessage(),
                        responseDto.getData()
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping()
    public ResponseEntity<StandardResponse> get(){
        List<CategoryDto> categoryDtoList = categoryService.getCategories();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Categories",
                        categoryDtoList
                ), HttpStatus.OK
        );
    }
    @GetMapping(path = "/by-id",params = {"id"})
    public ResponseEntity<StandardResponse> getCategoryById(
            @RequestParam(value = "id") String id
    ) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl Categories of Id " + id,
                        categoryDto
                ), HttpStatus.OK
        );
    }
}
