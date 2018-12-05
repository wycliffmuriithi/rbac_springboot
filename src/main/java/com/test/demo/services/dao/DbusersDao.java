package com.test.demo.services.dao;

import com.test.demo.database.DbusersRepo;
import com.test.demo.database.entities.DBUsers;
import com.test.demo.database.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DbusersDao {
    @Autowired
    DbusersRepo dbusersRepo;
    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepo roleRepo;

    @Transactional
    public Optional<DBUsers> loadUserByusername(String username) {
        List<DBUsers> dbusersList = dbusersRepo.findByUsername(username);
        if (dbusersList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(dbusersList.get(0));
        }
    }

    @Transactional
    public boolean registerUser(String username, String password) {
        List<DBUsers> dbusersList = dbusersRepo.findByUsername(username);
        if (dbusersList.isEmpty()) {
            DBUsers dbUser = new DBUsers();
            dbUser.setUsername(username);
            dbUser.setPassword(encoder.encode(password));
            dbUser.setRoles(new HashSet<>(roleRepo.findAll()));

            dbusersRepo.save(dbUser);
           return true;
        } else {
            //user already registered
           return false;
        }

    }

}
