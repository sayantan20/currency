package com.currency.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class ExchangeApplication {

	public static void main(String[] args) {
		try {
			Thread.sleep(30*1000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		SpringApplication.run(ExchangeApplication.class, args);
	}

}
