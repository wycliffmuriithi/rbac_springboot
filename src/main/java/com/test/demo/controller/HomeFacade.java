package com.test.demo.controller;

import com.test.demo.controller.models.UserModel;
import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path="/home")
public class HomeFacade {
    private static final Logger LOGGER = Logger.getLogger(HomeFacade.class);

    @RequestMapping(path="/index",method = RequestMethod.POST)
    public ModelAndView userHome(@ModelAttribute UserModel usermodel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("Logged in as "+auth.getName()+" with roles ");
        auth.getAuthorities().forEach((role)->{
            LOGGER.info("Role:"+role.getAuthority());
        });
        /* authenticated user */
        ModelAndView mv = new ModelAndView("Home");
        mv.addObject("usermodel",usermodel);
        return mv;
    }
}
