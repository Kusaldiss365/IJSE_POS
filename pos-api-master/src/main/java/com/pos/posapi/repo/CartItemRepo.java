package com.pos.posapi.repo;

import com.pos.posapi.enity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CartItemRepo extends JpaRepository<CartItem, String> {
    @Query(value = "SELECT * FROM cart_item WHERE item_id=?1 AND cart_id=?1", nativeQuery = true)
    Optional<CartItem> findByItemIdAndCartId(String itemId, String cartId);

    @Query(value = "SELECT * FROM cart_item WHERE cart_item_id=?1",nativeQuery = true)
    CartItem findCartItemById(String id);

    @Query(value = "SELECT cart_item_id FROM cart_item WHERE cart_item_id like ?% ORDER BY CAST(SUBSTRING(cart_item_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
