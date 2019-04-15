package com.example.securitytest.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import static org.apache.commons.lang3.BooleanUtils.and;

/**
 * Created by hx on 2019-04-09.
 */

/*
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

/*
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

/*
 * 第三种方法
 * LDAP认证
 */
//@Configuration
//@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
//public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter
//    @Autowired
//    DataSource dataSource;// 用于sql,自动装配,需要在pom.xml中配置【mybatis-spring-boot-starter】
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")// userSearchBase提供查找用户的基础查询
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")// groupSearchBase提供查找组的基础查询
//                .groupSearchFilter("member={0}")
//                .passwordCompare()// 通过密码比对进行认证
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode");
//    }
//}

/*
 * 第四种方法
 * LDAP认证,修改端口
 */
//@Configuration
//@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
//public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter
//    @Autowired
//    DataSource dataSource;// 用于sql,自动装配,需要在pom.xml中配置【mybatis-spring-boot-starter】
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")// userSearchBase提供查找用户的基础查询
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")// groupSearchBase提供查找组的基础查询
//                .groupSearchFilter("member={0}")
//                .contextSource()// 默认ldap认证假设监听本机33389端口,但是可用contextSource进行配置
////                .url("ldap://habuma.com:389/dc=habuma,dc=com");
//                .root("dc=habuma,dc=com")// 如果没有现成的LDAP服务器,可直接使用【.root("dc=habuma,dc=com")】
//                .ldif("classpath:users.ldif");// 指定LDIF文件
//    }
//}

/*
 * 第五种方法(1)
 * 拦截请求
 */
//@Configuration
//@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
//public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/spitters/me").authenticated()// 可以指定多个路径用逗号分隔
//                .antMatchers(HttpMethod.POST, "/spittles").authenticated()
//                .anyRequest().permitAll();// 说明其他所有请求都是允许的，不需要认证和任何的权限
//        // 可以试用通配符指定路径 EX: .regexMatchers("/spitters/.*").authenticates();
//        // authenticated()以及permitAll()用于定义如何保护路径。
//    }
//}

/*
 * 第五种方法(2)
 * 拦截请求
 */
//@Configuration
//@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
//public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                // 使用hasRole()，自动使用ROLE_前缀
//                .antMatchers("/spitter/me").hasRole("SPITTER")
////                .antMatchers(HttpMethod.POST, "/spittles").hasRole("ROLE_SPITTER")
//                .antMatchers("/spitters/me").hasAnyAuthority("ROLE_SPITTER")// 可以指定多个路径用逗号分隔
//                .antMatchers(HttpMethod.POST, "/spittles").hasAnyAuthority("ROLE_SPITTER")
//                .anyRequest().permitAll()// 说明其他所有请求都是允许的，不需要认证和任何的权限
//                .and()
//                .requiresChannel()// 未选定的URL强制使用https
////                .antMatchers("/").requiresInsecure();// 首页不包含敏感信息，可不是用https传送
//                .antMatchers("/spitter/form").requiresSecure();
//    }
//}
/*
 * 第六种方法
 * 启用默认登录页
 */
@Configuration
@EnableWebMvc //启用Web安全功能使用EnableWebSecurity, 如果是MVC，则使用如左,EnableWebMvcSecurity过时
public class SecurityConfig extends WebSecurityConfigurerAdapter {// 用于扩展 WebSecurityConfigurerAdapter

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/spitter/me").hasRole("SPITTER")
                .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
                .anyRequest().permitAll()
                .and()
                .requiresChannel()
                .antMatchers("/spitter/form").requiresSecure();
    }
}