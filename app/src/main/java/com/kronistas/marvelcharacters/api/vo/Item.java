package com.kronistas.marvelcharacters.api.vo;

/**
 * Created by Trey Robinson on 2/16/14.
 */
public class Item {

    public String resourceURI;
    public String name;
    public String type;

    @Override
    public String toString() {
        return "Item{" +
                "resourceURI='" + resourceURI + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
