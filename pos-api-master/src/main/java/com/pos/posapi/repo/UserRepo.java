package com.pos.posapi.repo;

import com.pos.posapi.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM user WHERE user_name=?1", nativeQuery = true)
    User findByUsername(String username);
    @Query(value = "SELECT user_id FROM user WHERE user_id like ?% ORDER BY CAST(SUBSTRING(user_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);
    @Query(value = "SELECT * FROM user WHERE email=?1", nativeQuery = true)
    User findUserByEmail(String username);

    @Query(value = "SELECT * FROM user WHERE user_id=?1", nativeQuery = true)
    Optional<User> findUserById(String userId);
}
