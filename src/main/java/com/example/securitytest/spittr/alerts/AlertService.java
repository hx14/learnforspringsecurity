package com.example.securitytest.spittr.alerts;

import com.example.securitytest.spittr.domain.Spittle;

/**
 * Created by hx on 2019-04-16.
 */
public interface AlertService {
    void sendSpittleAlert(Spittle spittle);
}
