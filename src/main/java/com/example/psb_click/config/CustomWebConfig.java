package com.example.psb_click.config;

import com.example.psb_click.util.InterceptLog;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

import static org.springdoc.core.Constants.CLASSPATH_RESOURCE_LOCATION;

@RequiredArgsConstructor
@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

    private  final InterceptLog interceptLog;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptLog);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations(CLASSPATH_RESOURCE_LOCATION+"/webjars/")
//                .resourceChain(true)
//                .addResolver(new WebJarsResourceResolver());
//    }

}
