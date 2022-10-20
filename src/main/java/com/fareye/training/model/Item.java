package com.fareye.training.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {
    private Integer itemCode;
    private String itemName;

    // private static Item instance;

    // synchronized public static Item getInstance() {
    // if (instance == null) {
    // instance = new Item(12, "Item Instance 1");

    // }
    // return instance;
    // }

    // public static Item instance;
    // static {
    // instance = new Item(12, "static class instance");
    // }

    private static Item instance;

    public static Item getInstance() {
        if (instance == null) {
            // synchronized block to remove overhead
            synchronized (Item.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new Item(12, "new object");
                }

            }
        }
        return instance;
    }

    public Item(Integer itemCode, String itemName) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
    }
}
