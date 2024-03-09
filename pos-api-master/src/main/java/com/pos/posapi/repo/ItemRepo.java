package com.pos.posapi.repo;

import com.pos.posapi.enity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item, String> {

    @Query(value = "SELECT * FROM cart_item WHERE cart_item_id=?1", nativeQuery = true)
    List<Item> findAllItemsByCartItemId(String cartItemId);
    @Query(value = "SELECT item_id FROM item WHERE item_id like ?% ORDER BY CAST(SUBSTRING(item_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
