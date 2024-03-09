package com.pos.posapi.repo;


import com.pos.posapi.enity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CategoryRepo extends JpaRepository<Category,String> {
    @Query(value = "SELECT category_id FROM category WHERE category_id like ?% ORDER BY CAST(SUBSTRING(category_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
}
