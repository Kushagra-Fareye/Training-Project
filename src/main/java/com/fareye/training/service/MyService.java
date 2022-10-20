package com.fareye.training.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fareye.training.model.Item;

@Service
public class MyService {
    // @Autowired
    private Item item = Item.getInstance();
    // private Item item = Item.instance;

    // @Autowired
    // @Qualifier("item2")
    // private Item item2;
    private Item item2 = Item.getInstance();
    // private Item item2 = Item.instance;

    // Java code to explain double check locking

    public void printItem() {
        System.out.println(item.toString());
        System.out.println(item2.toString());
    }
}
