package com.example.psb_click.config;

import com.example.psb_click.util.InterceptLog;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

    private  final InterceptLog interceptLog;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptLog);
    }

}
