package com.pos.posapi.service.impl;


import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.requestdto.RequestItemSaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.enity.Category;
import com.pos.posapi.enity.Item;
import com.pos.posapi.enity.Stock;
import com.pos.posapi.exception.EntryDuplicateException;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.repo.CategoryRepo;
import com.pos.posapi.repo.ItemRepo;
import com.pos.posapi.repo.StockRepo;
import com.pos.posapi.service.ItemService;
import com.pos.posapi.util.mapper.CategoryMapper;
import com.pos.posapi.util.mapper.ItemMapper;
import com.pos.posapi.util.mapper.StockMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepo itemRepo;
    private final ItemMapper itemMapper;
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;
    private final StockRepo stockRepo;
    private final StockMapper stockMapper;

    public ItemServiceImpl(ItemRepo itemRepo, ItemMapper itemMapper, CategoryRepo categoryRepo, CategoryMapper categoryMapper, StockRepo stockRepo, StockMapper stockMapper) {
        this.itemRepo = itemRepo;
        this.itemMapper = itemMapper;
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
        this.stockRepo = stockRepo;
        this.stockMapper = stockMapper;
    }

    @Override
    public CommonResponseDTO createItem(String categoryId,String stockId, RequestItemSaveDto itemDto) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isEmpty()) {
            throw new EntryNotFoundException("Category Not Found");
        }
        Optional<Stock> stock = stockRepo.findById(stockId);
        if (stock.isEmpty()) {
            throw new EntryNotFoundException("Stock Not Found");
        }
        String lastId = itemRepo.findLastId("POS-I-", 7);

        String id = "POS-I-1";

        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split("POS-I-")[1])) + 1;
            id = "POS-I-" + i;
        }

        ItemDto itemDto1 = new ItemDto(
                id,
                itemDto.getItemName(),
                itemDto.getItemQuantity(),
                itemDto.getItemUnitPrice(),
                itemDto.getItemDescription(),
                itemDto.isActiveState(),
                categoryMapper.toCategoryDto(category.get()),
                stockMapper.toStockDto(stock.get())
        );

        if (!itemRepo.existsById(itemDto1.getItemId())){
            itemRepo.save(itemMapper.toItem(itemDto1));
        }else {
            throw new EntryDuplicateException("This Item Already Exists");
        }
        return new CommonResponseDTO(
                201,
                "ITEM CREATED SUCCESSFULLY",
                itemDto1.getItemId(),
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO updateItem(String id, RequestItemSaveDto itemDto) {
        Optional<Item> item = itemRepo.findById(id);
        if (item.isEmpty()){
            throw new EntryNotFoundException("Customer Not Exists");
        }

        Item selectedItem = item.get();
        selectedItem.setItemName(itemDto.getItemName());
        selectedItem.setItemQuantity(itemDto.getItemQuantity());
        selectedItem.setItemUnitPrice(itemDto.getItemUnitPrice());
        selectedItem.setActiveState(itemDto.isActiveState());
        itemRepo.save(selectedItem);

        return new CommonResponseDTO(
                200,
                "ITEM UPDATED SUCCESSFULLY",
                selectedItem.getItemId(),
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO deleteItem(String id) {
        if (!itemRepo.existsById(id)){
            throw new EntryNotFoundException("Item Not Found");
        }
        itemRepo.deleteById(id);
        return new CommonResponseDTO(
                204,
                "ITEM DELETED SUCCESSFULLY",
                "",
                new ArrayList<>()
        );
    }

    @Override
    public List<ItemDto> getItems() {
        List<Item> items = itemRepo.findAll();
        if (items.isEmpty()) {
            throw new EntryNotFoundException("No Items Found");
        }
        return itemMapper.toItemDtoList(items);
    }

    @Override
    public ItemDto getItemById(String id) {
        Item item = itemRepo.getById(id);
        return itemMapper.toItemDto(item);
    }
}
