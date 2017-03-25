package com.kronistas.marvelcharacters.api.vo;

/**
 * Created by Trey Robinson on 2/12/14.
 */
public class ImageInfo {
    public String path;
    public String extension;

    @Override
    public String toString() {
        return "ImageInfo{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
