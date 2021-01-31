package com.nesder.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = {"classpath:message_zh.properties"})
public class PropertiesUtils implements EnvironmentAware {

    @Autowired
    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
    	PropertiesUtils.environment = environment;
    }
    
    public static String getEnvConfig(String code){
        String name = environment.getProperty(code);
        return name;
    }

}