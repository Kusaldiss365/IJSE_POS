package com.pos.posapi.service.impl;

import com.pos.posapi.dto.CartItemDto;
import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.responsedto.ResponseCartItemDto;
import com.pos.posapi.dto.responsedto.ResponseItemDto;
import com.pos.posapi.dto.responsedto.ResponseUserDataDTO;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.enity.*;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.repo.CartItemRepo;
import com.pos.posapi.repo.CartRepo;
import com.pos.posapi.repo.ItemRepo;
import com.pos.posapi.repo.UserRepo;
import com.pos.posapi.service.CartItemService;
import com.pos.posapi.service.UserService;
import com.pos.posapi.util.mapper.CartItemMapper;
import com.pos.posapi.util.mapper.CartMapper;
import com.pos.posapi.util.mapper.ItemMapper;
import com.pos.posapi.util.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepo cartItemRepo;
    private final CartItemMapper cartItemMapper;
    private final ItemRepo itemRepo;
    private final CartRepo cartRepo;
    private final UserService userService;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    public CartItemServiceImpl(CartItemRepo cartItemRepo, CartItemMapper cartItemMapper, ItemRepo itemRepo, CartRepo cartRepo, UserService userService, UserRepo userRepo, UserMapper userMapper, ItemMapper itemMapper, CartMapper cartMapper) {
        this.cartItemRepo = cartItemRepo;
        this.cartItemMapper = cartItemMapper;
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
        this.userService = userService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.itemMapper = itemMapper;
        this.cartMapper = cartMapper;
    }

    @Override
    @Transactional
    public CommonResponseDTO addItemToCart(String itemId, String cartId, int quantity, String token) {
        Optional<Item> itemOptional = itemRepo.findById(itemId);
        if (itemOptional.isEmpty()) {
            throw new EntryNotFoundException("No Items Found");
        }

        Optional<Cart> cartOptional = cartRepo.findById(cartId);
        if (cartOptional.isEmpty()) {
            throw new EntryNotFoundException("Cart Empty");
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String lastId = cartItemRepo.findLastId("POS-CA-", 8);

        String id = "POS-CA-1";

        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split("POS-CA-")[1])) + 1;
            id = "POS-CA-" + i;
        }

        ResponseUserDataDTO userData = userService.getAllUserData(token);
        Optional<User> userOptional = userRepo.findUserById(userData.getUserId());
        if (userOptional.isEmpty()) {
            throw new EntryNotFoundException("User Not Found");
        }

        Item item = itemOptional.get();
        Cart cart = cartOptional.get();
        User user = userOptional.get();

        Optional<CartItem> existingCartItemOptional = cartItemRepo.findByItemIdAndCartId(itemId, cartId);
        if (existingCartItemOptional.isPresent()) {
            CartItem existingCartItem = existingCartItemOptional.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepo.save(existingCartItem);
        } else {
            CartItemDto cartItemDto = new CartItemDto(
                    id,
                    itemMapper.toItemDto(item),
                    cartMapper.toCartDto(cart),
                    userMapper.toUserDto(user),
                    quantity
            );
            cartItemRepo.save(cartItemMapper.toCartItem(cartItemDto));
            return new CommonResponseDTO(
                    201,
                    "ITEM ADDED TO CART SUCCESSFULLY",
                    cartItemDto.getCartItemId(),
                    new ArrayList<>()
            );
        }
        return new CommonResponseDTO(
                201,
                "ITEM ADDED TO CART SUCCESSFULLY",
                existingCartItemOptional.get().getCartItemId(),
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO deleteCartItem(String id) {
        return null;
    }

    @Override
    public CartItemDto getCartItemById(String id) {
        CartItem cartItem = cartItemRepo.findCartItemById(id);
        return cartItemMapper.toCartItemDto(cartItem);
    }

    @Override
    public List<CartItemDto> getCartItems() {
        List<CartItem> cartItems = cartItemRepo.findAll();
        if (cartItems.isEmpty()) {
            throw new EntryNotFoundException("No Cart Items Found");
        }
        return cartItemMapper.toCartItemDtoList(cartItems);
    }

    @Override
    public List<ResponseCartItemDto> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepo.findAll();
        if (!cartItems.isEmpty()){
            List<ResponseCartItemDto> responseCartItemDtoList = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                List<Item> item = itemRepo.findAllItemsByCartItemId(cartItem.getCartItemId());
                ResponseCartItemDto responseCartItemDto = new ResponseCartItemDto(
                        cartItem.getCartItemId(),
                        cartItem.getQuantity(),
                        itemMapper.toResponseItemDtoList(item)
                );
                responseCartItemDtoList.add(responseCartItemDto);
            }
            return responseCartItemDtoList;

        }else {
            throw new EntryNotFoundException("No Items Found");
        }
    }
}
