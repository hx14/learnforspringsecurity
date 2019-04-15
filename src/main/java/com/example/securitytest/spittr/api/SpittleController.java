package com.example.securitytest.spittr.api;

import com.example.securitytest.spittr.Spittle;
import com.example.securitytest.spittr.data.SpitterRespository;
import com.example.securitytest.spittr.data.SpittleRespository;
import com.example.securitytest.spittr.web.DuplicateSpittleException;
import com.example.securitytest.spittr.web.SpittleForm;
import com.example.securitytest.spittr.web.SpittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by hx on 2019-04-15.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private static final String MAX_LONG_AS_STRING = "9223372036584775807";
    private SpittleRespository spittleRespository;

    @Autowired
    public SpittleController(SpittleRespository spittleRespository) {
        this.spittleRespository = spittleRespository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        model.addAttribute(spittleRespository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleRespository.findSpittles(max, count);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showSpittles(
            @RequestParam("spittle_id") long spittleId, Model model
    ) {
        model.addAttribute(spittleRespository.findOne(spittleId));
        return "spittles";
    }

    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
        Spittle spittle = spittleRespository.findOne(spittleId);
        if (spittle == null) {
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spittle);
        return "spittle";
    }
    @RequestMapping(method = RequestMethod.POST)
    public  String saveSpittle(SpittleForm form, Model model) {
        spittleRespository.save(new Spittle(form.getMessage(),new Date(),form.getLongitude(),form.getLatitude()));
        return "redirect:/spittles";
    }
    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittle() {
        return "error/duplicate";
    }
}
