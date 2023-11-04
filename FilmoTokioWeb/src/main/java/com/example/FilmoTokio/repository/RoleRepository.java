package com.example.FilmoTokio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.FilmoTokio.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    
    @Query("SELECT r FROM Role r INNER JOIN User u ON r.id = u.id WHERE u.username LIKE '?1'")
    Role findByUsername (String username);
}
