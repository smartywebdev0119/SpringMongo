package com.emirates.springsample;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Simple Users Service Application main class.
 *
 * @author alex
 */
@SpringBootApplication
@EnableCaching
public class SimpleUsersServiceApplication {

    @Autowired
    private Logger logger;

    public static void main(String[] args) {
        SpringApplication.run(SimpleUsersServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> logger.info("Application is running.");
    }

    @Bean
    public CacheManager cacheManager() {
        //Create a simple HashMap based CacheManager
        return new ConcurrentMapCacheManager();
    }

}
