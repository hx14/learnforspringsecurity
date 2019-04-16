package com.example.securitytest.spittr.api;

import com.example.securitytest.spittr.domain.Spittle;
import com.example.securitytest.spittr.config.Error;
import com.example.securitytest.spittr.data.SpittleRespository;
import com.example.securitytest.spittr.web.DuplicateSpittleException;
import com.example.securitytest.spittr.web.SpittleForm;
import com.example.securitytest.spittr.web.SpittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sun.java2d.cmm.Profile;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hx on 2019-04-15.
 */
//@Controller
@RestController // 如果使用这个时候,可以省略@RequestBody
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
    public @ResponseBody
    List<Spittle> spittles(
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
            throw new SpittleNotFoundException(spittleId);
        }
        model.addAttribute(spittle);
        return "spittle";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(SpittleForm form, Model model) {
        spittleRespository.save(new Spittle(form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
        return "redirect:/spittles";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
        Spittle spittle1 = spittleRespository.save(spittle);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri =
                ucb.path("/spittles")
                        .path(String.valueOf(spittle1.getId()))
                        .build()
                        .toUri();
        headers.setLocation(locationUri);
        ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(spittle1, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittle() {
        return "error/duplicate";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Spittle spittleById(@PathVariable long id) {
        Spittle spittle = spittleRespository.findOne(id);
        if (spittle == null) {
            throw new SpittleNotFoundException(id);
        }
        return spittle;
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error spittleNotFound(SpittleNotFoundException e) {
        long spittleId = e.getSpittleId();
        return new Error(4, "Spittle [" + spittleId + "] not found");
    }

    public Profile fetchFacebookProfile(String id) {
        Map<String, String> urlVariable = new HashMap<>();
        urlVariable.put("id", id);
        RestTemplate rest = new RestTemplate();
        return rest.getForObject("http://graph.facebook.com/{spitter}", Profile.class, urlVariable);
    }
}
