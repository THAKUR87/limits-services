package com.pawan.spring.microservices.limitsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pawan.spring.microservices.limitsservices.bean.LimitConfiguration;

@RestController
@EnableHystrix
public class LimitsConfigurationController {
	
	@Autowired
	Configuration config;
	
	@GetMapping(path="/limits")
	public LimitConfiguration reteriveLimitsFromConfigurations() {
		return new LimitConfiguration(config.getMaximum(), config.getMinimum());
	}
	
	@GetMapping(path="/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallBackReteriveConfiguration")
	public LimitConfiguration reteriveConfigurations() {
		throw new RuntimeException("Not avaiable");
	}
	
	public LimitConfiguration fallBackReteriveConfiguration() {
		return new LimitConfiguration(9, 999);
	}

}
