package com.iflysse.helper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor( new LoginInterceptor() )
        .addPathPatterns("/**")
        .excludePathPatterns("/login/**")               //排除部分访问路径不拦截
        .excludePathPatterns("/css/**")
        .excludePathPatterns("fonts/**")
        .excludePathPatterns("/bootstrap/**")
        .excludePathPatterns("/images/**")
        .excludePathPatterns("/js/**");
        
        registry.addInterceptor( new AdminInterceptor() )
        .addPathPatterns("admin/**")
        .addPathPatterns("term/**")
        .excludePathPatterns("term/term_list") //获取学期列表
        .excludePathPatterns("term/term_current"); //获取当前学期
    }
}