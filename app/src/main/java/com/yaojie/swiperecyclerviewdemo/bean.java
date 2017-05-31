package com.yaojie.swiperecyclerviewdemo;

/**
 * Created by GEOFLY on 2017/5/25.
 */

public class bean {
    private String name;
    private int imageId;

    public bean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
