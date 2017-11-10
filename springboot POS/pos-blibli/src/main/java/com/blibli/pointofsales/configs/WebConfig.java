package com.blibli.pointofsales.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override //buat static file, mengarahin file ke mana
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/css/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/templates/bootstrap/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/templates/image/");
    }
}