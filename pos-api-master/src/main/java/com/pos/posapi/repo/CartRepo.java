package com.pos.posapi.repo;

import com.pos.posapi.enity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CartRepo extends JpaRepository<Cart,String> {
    @Query(value = "SELECT cart_id FROM cart WHERE cart_id like ?% ORDER BY CAST(SUBSTRING(cart_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
