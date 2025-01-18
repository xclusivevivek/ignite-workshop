package com.vvsoft.ignite_workshop;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IgniteWorkshopApplication {

	private static final Logger log = LoggerFactory.getLogger(IgniteWorkshopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(IgniteWorkshopApplication.class, args);
	}

	@Bean
	public ApplicationRunner appRunner(){
		return args -> {
			ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
			try (IgniteClient client = Ignition.startClient(cfg)) {

				ClientCache<Integer, String> cache = client.getOrCreateCache("myCache");
				cache.put(1,"Hello");
				log.info(cache.get(1));
			}
		};
	}

}
