package com.kronistas.marvelcharacters.api.vo;

import java.util.List;

/**
 * Created by Trey Robinson on 2/16/14.
 */
public class ItemList {

    public int available;
    public int returned;
    public String collectionURI;
    public List<Item> items;

    @Override
    public String toString() {
        return "ItemList{" +
                "available=" + available +
                ", returned=" + returned +
                ", collectionURI='" + collectionURI + '\'' +
                '}';
    }
}
