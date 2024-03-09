package com.pos.posapi.repo;

import com.pos.posapi.enity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StockRepo extends JpaRepository<Stock,String> {
    @Query(value = "SELECT stock_id FROM stock WHERE stock_id like ?% ORDER BY CAST(SUBSTRING(stock_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
