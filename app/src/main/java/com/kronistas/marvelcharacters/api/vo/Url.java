package com.kronistas.marvelcharacters.api.vo;

/**
 * Created by Trey Robinson on 2/13/14.
 */
public class Url {
    public String type;
    public String url;

    @Override
    public String toString() {
        return "Url{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
