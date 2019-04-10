package com.example.securitytest.spittr.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by hx on 2019-04-10.
 */
// 认证的用户储存在非关系型数据库中,如Neo4j,提供下列接口
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
