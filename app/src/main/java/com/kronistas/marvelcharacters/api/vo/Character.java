package com.kronistas.marvelcharacters.api.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by Trey Robinson on 2/12/14.
 */
public class Character {

    public int id;
    public String name;
    public String description;
    public Date modified;
    public String resourceURI;
    public List<Url> urls;
    public ImageInfo thumbnail;
    public ItemList comics;
    public ItemList stories;
    public ItemList events;
    public ItemList series;

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", modified=" + modified +
                ", resourceURI='" + resourceURI + '\'' +

                ", thumbnail=" + thumbnail.toString() +
                ", comics=" + comics +
                ", stories=" + stories +
                ", events=" + events +
                ", series=" + series +
                '}';
    }
}
