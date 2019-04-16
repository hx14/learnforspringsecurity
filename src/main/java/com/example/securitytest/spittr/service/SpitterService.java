package com.example.securitytest.spittr.service;

import com.example.securitytest.spittr.domain.Spitter;
import com.example.securitytest.spittr.domain.Spittle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2019-04-15.
 */
public class SpitterService {
    public Spittle saveSpitter(Spittle spittle) {
        return spittle;
    }

    public long deleteSpitter(long spittle) {
        return spittle;
    }

    public List<Spittle> getRecentSpittles(int spittleCount) {
        List<Spittle> spittleList = new ArrayList<>();
        return spittleList;
    }

    public List<Spittle> getspittleForSpitter(Spitter spitter) {
        List<Spittle> spittleList = new ArrayList<>();
        return spittleList;
    }
}
