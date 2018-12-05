package com.test.demo.services;

import com.test.demo.database.entities.DBUsers;
import com.test.demo.database.entities.Role;
import com.test.demo.services.dao.DbusersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    DbusersDao dbusersDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){
        Optional<DBUsers> databaseusercontainer = dbusersDao.loadUserByusername(username);

        UserBuilder builder = null;
        if(databaseusercontainer.isPresent()){
            DBUsers databaseuser = databaseusercontainer.get();
            builder = User.withUsername(username);
            builder.password(databaseuser.getPassword());
            builder.authorities(loadUserRoles(databaseuser));
        }else{
            throw new UsernameNotFoundException(username);
        }
        return builder.build();
    }



    private List loadUserRoles(DBUsers username){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : username.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }
}
