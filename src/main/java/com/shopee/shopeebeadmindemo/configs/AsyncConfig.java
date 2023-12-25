package com.shopee.shopeebeadmindemo.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

//Spring Event
//Scan component will excute event
@Configuration
@ComponentScan("com.shopee.shopeebeadmindemo.events.listener")
@EnableRetry
@EnableAsync
public class AsyncConfig {
}