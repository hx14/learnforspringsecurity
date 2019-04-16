package com.example.securitytest.spittr.data;

import com.example.securitytest.spittr.domain.Spitter;

/**
 * Created by hx on 2019-04-10.
 */
public interface SpitterRespository {
    Spitter findByUsername(String username);
    void save(Spitter spitter);
}
