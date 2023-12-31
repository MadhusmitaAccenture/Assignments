package com.accenture.lkm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class PizzaOrderConsumer {

	public static void main(String[] args){
		SpringApplication.run(PizzaOrderConsumer.class, args);
	}
	
    /**
     * Used for exporting span data to Zipkin
     * 
     * @return
     */
    @Bean
    public AlwaysSampler bean(){
    	return new AlwaysSampler();
    }
}