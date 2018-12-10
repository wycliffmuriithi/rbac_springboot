package com.test.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorFacade {
    @RequestMapping(path = "/denied")
    public ModelAndView get401Page() {
        ModelAndView mv = new ModelAndView("Denied");
        return mv;
    }
}
