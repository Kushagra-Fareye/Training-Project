package com.fareye.training.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Properties {
    private String propertyKey;
    private String propertyValue;
    

    public Properties(String PropertyKey,String PropertyValue){
        this.setPropertyKey(PropertyKey);
        this.setPropertyValue(PropertyValue);
    }
}
