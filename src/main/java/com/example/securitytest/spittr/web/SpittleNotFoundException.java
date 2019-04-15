package com.example.securitytest.spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by hx on 2019-04-15.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "spittle Not Found")
public class SpittleNotFoundException extends RuntimeException {
}
