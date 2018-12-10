package com.test.demo.controller;


import com.test.demo.controller.models.UserModel;
import com.test.demo.services.dao.DbusersDao;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class LoginFacade {
    @Autowired
    DbusersDao dbusersDao;
    private static final Logger LOGGER = Logger.getLogger(LoginFacade.class);


    /**
     * retrieve the login page
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        ModelAndView mv = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(!auth.getAuthorities().isEmpty()){
//            mv.setViewName("Login");
//        }else {
            mv.setViewName("Login");
            mv.addObject("usermodel", new UserModel());
//        }
        return mv;
    }

    /**
     * handle incorrect login details
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView processLoginResult(ModelAndView modelAndView, @ModelAttribute("usermodel") UserModel usermodel) {
        modelAndView.setViewName("Login");
        modelAndView.addObject("incorrectdetails",true);
        return modelAndView;
    }

    /**
     * retrieve the registration page
     * @param modelAndView
     * @param userModel
     * @return
     */
    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage(ModelAndView modelAndView, UserModel userModel) {
        modelAndView.setViewName("Register");
        modelAndView.addObject("user", userModel);
        return modelAndView;
    }

    /**
     * post the registration results to backend
     * @param modelAndView
     * @param usermodel
     * @param bindingResult
     * @return
     */
    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registernewUser(ModelAndView modelAndView, @ModelAttribute("user") UserModel usermodel, BindingResult bindingResult) {
        LOGGER.info("Sign up object " + usermodel.toString());
        modelAndView.setViewName("Register");

        if(!usermodel.getPassword().equals(usermodel.getConfirmPassword())){
            bindingResult.rejectValue("password","error.password","The passwords do not match");
        }else{
            boolean registrationresult = dbusersDao.registerUser(usermodel.getUsername(), usermodel.getPassword());
            if(!registrationresult){
                bindingResult.rejectValue("username","error.email","Duplicate registration details detected");
            }else{
                modelAndView.addObject("paramsuccess",true);
            }
        }
        return modelAndView;
    }


}
