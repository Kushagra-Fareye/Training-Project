package com.fareye.training.controller;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fareye.training.model.Properties;

@RestController
public class PropertyController {

    @Autowired
    private Environment env;


    List<Properties> properties = new ArrayList<>();
    
    @GetMapping("/applicationProperties")
    public List<Properties> getApplicationProperties(){
        MutablePropertySources propertySources = ((AbstractEnvironment) env).getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            String propertyKey = propertySource.getName();
            String propertyValue = env.getProperty(propertyKey);
            Properties property = new Properties(propertyKey,propertyValue);
			properties.add(property);
		}
        return properties;
    }

    @GetMapping("/applicationProperty")
    public String getApplicationProperty(@RequestParam String propertyKey){
        String propertyValue = env.getProperty(propertyKey);
        if(propertyValue.length() > 0){
            Properties property = new Properties(propertyKey,propertyValue);
            properties.add(property);
        }
        return env.getProperty(propertyKey);
    }
}
