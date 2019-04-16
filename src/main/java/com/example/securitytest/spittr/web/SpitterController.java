package com.example.securitytest.spittr.web;

import com.example.securitytest.spittr.domain.Spitter;
import com.example.securitytest.spittr.data.SpitterRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by hx on 2019-04-15.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRespository spitterRespository;

    @Autowired
    public SpitterController(SpitterRespository spitterRespository) {
        this.spitterRespository = spitterRespository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration (@Valid Spitter spitter, Errors errors){
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spitterRespository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(
            @PathVariable String username, Model model
    ) {
        Spitter spitter = spitterRespository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }
}
