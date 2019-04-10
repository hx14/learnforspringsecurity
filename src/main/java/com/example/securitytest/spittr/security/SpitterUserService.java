package com.example.securitytest.spittr.security;

import com.example.securitytest.spittr.Spitter;
import com.example.securitytest.spittr.data.SpitterRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2019-04-10.
 */
public class SpitterUserService implements UserDetailsService {
    // SpitterRespository能够从关系型数据库等中查找spitter对象。或者进行伪造
    private final SpitterRespository spitterRespository;
//
    public SpitterUserService(SpitterRespository spitterRespository) {
        this.spitterRespository = spitterRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Spitter spitter = spitterRespository.findByUsername(username);
        if (spitter != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
            return new User(spitter.getUsername(),
                    spitter.getPassword(),
                    authorities);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
//    @Autowired
//    SpitterRespository spitterRespository;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(new SpitterUserService(spitterRespository));
//    }
}
