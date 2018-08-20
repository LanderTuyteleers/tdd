package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class Config extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/tdd").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/rootUser.html").setViewName("rootUser");
        registry.addViewController("/rootUser").setViewName("rootUser");
        registry.addViewController("/normalUser.html").setViewName("normalUser");
        registry.addViewController("/normalUser").setViewName("normalUser");
    }
}
