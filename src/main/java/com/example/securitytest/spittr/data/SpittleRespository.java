package com.example.securitytest.spittr.data;

import com.example.securitytest.spittr.Spittle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by hx on 2019-04-15.
 */
public interface SpittleRespository {
    List<Spittle> findSpittles(long max, int count);
    Spittle findOne(long spittleId);
   void save(Spittle spittle);
}
