package com.test.demo.controller;


import com.test.demo.controller.models.UserModel;
import com.test.demo.services.dao.DbusersDao;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        ModelAndView mv = new ModelAndView("Login");
        mv.addObject("usermodel", new UserModel());
        return mv;
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
    public ModelAndView registernewUser(ModelAndView modelAndView, @ModelAttribute UserModel usermodel, BindingResult bindingResult, Errors errors) {
        LOGGER.info("Sign up object " + usermodel.toString());

        modelAndView.setViewName("Register");


        if(!usermodel.getPassword().equals(usermodel.getConfirmPassword())){
            bindingResult.rejectValue("password","error.password","The passwords do not match");
            errors.rejectValue("password","error.password","The passwords do not match");
        }else{
            boolean registrationresult = dbusersDao.registerUser(usermodel.getUsername(), usermodel.getPassword());
            if(!registrationresult){
                bindingResult.rejectValue("username","error.email","Duplicate registration details detected");
                errors.rejectValue("username","error.email","Duplicate registration details detected");
            }else{
                modelAndView.addObject("paramsuccess",true);
            }
        }
        for(ObjectError err:errors.getAllErrors()) {
            LOGGER.info("Reg errors " +err.getObjectName()+" failed "+err.toString());
        }
        modelAndView.addObject("user", usermodel);
        return modelAndView;
    }

    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public ModelAndView get401Page() {
        ModelAndView mv = new ModelAndView("Denied");
        return mv;
    }
}
