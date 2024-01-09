package com.shopee.ecommer.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

//Spring Event
//Scan component will executed event
@Configuration
@ComponentScan("com.shopee.ecommer.events.listener")
@EnableRetry
@EnableAsync
public class SpringEventConfig {
}