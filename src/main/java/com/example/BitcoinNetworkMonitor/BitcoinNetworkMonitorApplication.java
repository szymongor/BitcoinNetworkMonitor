package com.example.BitcoinNetworkMonitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BitcoinNetworkMonitorApplication {


	public static void main(String[] args) {
		SpringApplication.run(BitcoinNetworkMonitorApplication.class, args);
		log.info("Start app");
	}

}
