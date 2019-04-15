package com.example.securitytest.spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by hx on 2019-04-15.
 */
@Controller
@RequestMapping({"/","/homepage"})
public class HomeController {
    @RequestMapping(method = GET)
    public String home() {
        return "home";
    }
}
