package com.vvsoft.ignite_workshop;

import com.vvsoft.ignite_workshop.entity.Company;
import com.vvsoft.ignite_workshop.entity.Person;
import com.vvsoft.ignite_workshop.entity.PersonKey;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientCacheConfiguration;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

			ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10801");
			try (IgniteClient client = Ignition.startClient(cfg)) {

				ClientCache<PersonKey, Person> personCache = createOrGetCache(client, "Person");
				ClientCache<String, Company> companyCache = createOrGetCache(client, "Company");
				Person foo = new Person("foo","bar",34);
				Company bar = new Company("bar","Bar desc");
				personCache.put(new PersonKey("foo","bar"), foo);
				companyCache.put("bar",bar);
				log.info(personCache.get(new PersonKey("foo","bar")).toString());
				log.info(companyCache.get("bar").toString());
			}
		};
	}

	private static <K,V> ClientCache<K, V> createOrGetCache(IgniteClient client, String cacheName) {
		return client.getOrCreateCache(createCacheConfig(cacheName));
	}


	private static @NotNull ClientCacheConfiguration createCacheConfig(String cacheName) {
		ClientCacheConfiguration cacheConfig = new ClientCacheConfiguration();
		cacheConfig.setName(cacheName);
		cacheConfig.setCacheMode(CacheMode.PARTITIONED);
		cacheConfig.setBackups(1);
		cacheConfig.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
		return cacheConfig;
	}

}
