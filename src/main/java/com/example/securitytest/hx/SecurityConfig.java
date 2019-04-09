package com.example.securitytest.hx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by hx on 2019-04-09.
 * <p>
 * 第一种方法
 * 显式启用
 * <p>
 * 第一种方法
 * 显式启用
 * <p>
 * 第二种方法
 * 密码加密，使用数据库
 */

/**
 * 第一种方法
 * 显式启用
 */
//@Configuration
//@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
//public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //启用内存用户储存
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");
//    }
//}

/**
 * 第二种方法
 * 密码加密，使用数据库
 */
//@Configuration
//@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
//public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter
//    @Autowired
//    DataSource dataSource;// 用于sql,自动装配,需要在pom.xml中配置【mybatis-spring-boot-starter】
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username,password,true " +
//                                "from Spitter where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, 'ROLE_USER' from Spitter where username=?")
//                .passwordEncoder(new PasswordEncoder() {// 密码加密
//                    @Override
//                    public String encode(CharSequence charSequence) {
//                        return null;
//                    }
//
//                    @Override
//                    public boolean matches(CharSequence charSequence, String s) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean upgradeEncoding(String encodedPassword) {
//                        return false;
//                    }
//                });
//    }
//}

/**
 * 第三种方法
 * LDAP认证
 */
@Configuration
@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter
    @Autowired
    DataSource dataSource;// 用于sql,自动装配,需要在pom.xml中配置【mybatis-spring-boot-starter】

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase("ou=people")// userSearchBase提供查找用户的基础查询
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")// groupSearchBase提供查找组的基础查询
                .groupSearchFilter("member={0}")
                .passwordCompare()// 通过密码比对进行认证
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("passcode");
    }
}