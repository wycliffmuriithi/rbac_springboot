package com.test.demo.database;

import com.test.demo.database.entities.DBUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbusersRepo extends JpaRepository<DBUsers, Integer> {
    List<DBUsers> findByUsername(String username);
}
