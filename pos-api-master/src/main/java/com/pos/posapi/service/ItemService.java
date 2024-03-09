package com.pos.posapi.service;


import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.requestdto.RequestItemSaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface ItemService {
    CommonResponseDTO createItem(String categoryId,String stockId, RequestItemSaveDto itemDto);

    CommonResponseDTO updateItem(String id, RequestItemSaveDto itemDto);

    CommonResponseDTO deleteItem(String id);

    List<ItemDto> getItems();

    ItemDto getItemById(String id);
}
