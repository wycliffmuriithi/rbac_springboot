package com.test.demo.controller;

import com.test.demo.controller.models.UserModel;
import com.test.demo.database.entities.DBUsers;
import com.test.demo.services.dao.DbusersDao;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/app")
public class HomeFacade {
    private static final Logger LOGGER = Logger.getLogger(HomeFacade.class);
    @Autowired
    DbusersDao dbusersDao;

    @RequestMapping(path="/index")
    public ModelAndView userHome(){
        //load authenticated context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("Logged in as "+auth.getName()+" with roles ");
        auth.getAuthorities().forEach((role)->{
            LOGGER.info("Role:"+role.getAuthority());
        });
        /* fetch authenticated user details from database*/
        Optional<DBUsers> dbUsers = dbusersDao.loadUserByusername(auth.getName());
        ModelAndView mv = new ModelAndView("Home");
        mv.addObject("usermodel",dbUsers.get());
        return mv;
    }

    @RequestMapping(path="/users",method = RequestMethod.GET)
    public List<DBUsers> getallUsers(){
        return dbusersDao.allUsers();
    }
}
