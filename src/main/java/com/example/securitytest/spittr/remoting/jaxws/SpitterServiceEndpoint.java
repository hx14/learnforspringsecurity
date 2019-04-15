package com.example.securitytest.spittr.remoting.jaxws;

import com.example.securitytest.spittr.Spitter;
import com.example.securitytest.spittr.Spittle;
import com.example.securitytest.spittr.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by hx on 2019-04-15.
 */
@Component
@WebService
public class SpitterServiceEndpoint {
    @Autowired
    SpitterService spitterService;
    @WebMethod
    public void addSpittle(Spittle spittle) {
        spitterService.saveSpitter(spittle);
    }
    @WebMethod
    public void deleteSpittle(long spittleId) {
        spitterService.deleteSpitter(spittleId);
    }
    @WebMethod
    public List<Spittle> getRecentSpittles(int spittleCount) {
        return spitterService.getRecentSpittles(spittleCount);
    }
    @WebMethod
    public List<Spittle> getSpittleForSpitter(Spitter spitter) {
        return spitterService.getspittleForSpitter(spitter);
    }
}
