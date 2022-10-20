package com.fareye.training.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fareye.training.model.Item;

@Configuration
public class MyConfiguration {

    @Bean("item1")
    @Primary
    public Item getUniqueItem() {
        System.out.println("Making new item1.");
        return new Item(123, "item 1");
    }

    @Bean("item2")
    public Item getUniqueItem2() {
        System.out.println("Making new item2.");
        return new Item(124, "item 2");
    }
}
