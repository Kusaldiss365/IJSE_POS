package com.pos.posapi.controller;

import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.StockDto;
import com.pos.posapi.dto.requestdto.RequestStockDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.service.StockService;
import com.pos.posapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@CrossOrigin
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createStock(
            @RequestBody RequestStockDto stockDto
    ){
        CommonResponseDTO commonResponseDTO = stockService.createStock(stockDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.CREATED
        );
    }
    @GetMapping(path = "/by-id",params = {"id"})
    public ResponseEntity<StandardResponse> getStockById(
            @RequestParam(value = "id") String id
    ) {
        StockDto stockDto = stockService.getStockById(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl Stock of Id " + id,
                        stockDto
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(params={"id"})
    public ResponseEntity<StandardResponse> deleteStock(
        @RequestParam(value="id") String id
    ){
        CommonResponseDTO commonResponseDto = stockService.deleteStock(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDto.getCode(),
                        commonResponseDto.getMessage(),
                        commonResponseDto.getData()
                ),HttpStatus.NO_CONTENT
        );
    }

    @PutMapping(params={"id"})
    public ResponseEntity<StandardResponse> updateStock(
            @RequestParam(value="id") String id,
            @RequestBody RequestStockDto stockDto
    ){
        CommonResponseDTO commonResponseDto = stockService.updateStock(id,stockDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDto.getCode(),
                        commonResponseDto.getMessage(),
                        commonResponseDto.getData()
                ),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<StandardResponse> get(){
        List<StockDto> stockDtoList = stockService.getStocks();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Stocks",
                        stockDtoList
                ), HttpStatus.OK
        );
    }
}
