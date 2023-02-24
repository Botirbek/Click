package com.example.psb_click.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;


@Configuration
public class Beans {

    @Value("${variable.baseUrl}")
    private String baseUrl;
    @Value("${variable.proxyHost}")
    private String proxyHost;
    @Value("${variable.proxyPort}")
    private String proxyPort;

    @Bean
    public WebClient webClient(){
        HttpClient httpClient =
                HttpClient.create()
                        .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                                .host(proxyHost)
                                .port(Integer.parseInt(proxyPort)));

        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder().clientConnector(connector).baseUrl(baseUrl).build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }


}
