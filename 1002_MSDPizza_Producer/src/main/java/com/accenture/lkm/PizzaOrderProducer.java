package com.accenture.lkm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class PizzaOrderProducer {

    public static void main(String[] args) {
        SpringApplication.run(PizzaOrderProducer.class, args);
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