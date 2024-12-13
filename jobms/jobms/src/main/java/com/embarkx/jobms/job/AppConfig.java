package com.embarkx.jobms.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @LoadBalanced // With @LoadBalanced, the RestTemplate can use the service name as the base URL (e.g.,
    // "http://my-service/endpoint"), and the load balancer will distribute requests among the available
    // instances of the service.
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
