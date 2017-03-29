package com.emirates.springsample.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * A producer for {@link Logger}.
 *
 * @author alex
 */
@Configuration
public class LoggerProvider {

    @Bean
    @Scope("prototype") //TODO learn scopes
    public Logger produceLogger(InjectionPoint injectionPoint) {
        Class<?> clazz = injectionPoint.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(clazz);
    }
}