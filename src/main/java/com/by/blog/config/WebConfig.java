package com.by.blog.config;

import com.by.blog.security.AuthRoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hobo
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthRoleInterceptor authRoleInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authRoleInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/home/hobo/blog/**")
                .addResourceLocations("file:/home/hobo/blog/");
    }
}
