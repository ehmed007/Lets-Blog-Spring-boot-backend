package com.example.BlogApplication.Repositories;

import com.example.BlogApplication.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(nativeQuery = true, value = "Select username from users where users.username = ?1")
    public String findUserByUsername(String username);

    public Optional<Users> findByUsername(String username);
}
