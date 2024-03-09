package com.pos.posapi.controller;

import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.requestdto.RequestItemSaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.service.ItemService;
import com.pos.posapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<StandardResponse> create(
            @RequestParam(value = "categoryId") String categoryId,
            @RequestParam(value = "stockId") String stockId,
            @RequestBody RequestItemSaveDto itemDto){
        CommonResponseDTO responseDto = itemService.createItem(categoryId,stockId,itemDto);
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
            @RequestBody RequestItemSaveDto itemDto){
        CommonResponseDTO responseDto = itemService.updateItem(id,itemDto);
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
        CommonResponseDTO responseDto = itemService.deleteItem(id);
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
        List<ItemDto> itemDtoList = itemService.getItems();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Items",
                        itemDtoList
                ), HttpStatus.OK
        );
    }

    @GetMapping(path = "/by-id",params = {"id"})
    public ResponseEntity<StandardResponse> getItemById(
            @RequestParam(value = "id") String id
    ) {
        ItemDto itemDto = itemService.getItemById(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl Item of Id " + id,
                        itemDto
                ), HttpStatus.OK
        );
    }
}
