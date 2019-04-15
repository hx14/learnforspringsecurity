package com.example.securitytest.spittr.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by hx on 2019-04-15.
 */
public class MyServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Dynamic myServlet = servletContext.addServlet("myServlet", (Class<? extends Servlet>) MyServlet.class);
        myServlet.addMapping("/custom/**");
    }
}
