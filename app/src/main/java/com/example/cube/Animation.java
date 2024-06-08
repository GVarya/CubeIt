package com.example.cube;

import java.util.ArrayList;

public class Animation {

    private int id;
    private String name;
    private ArrayList<Image> images;
    boolean isDisplaying = false;

    public Animation(int id, String name, ArrayList<Image> images) {
        this.id = id;
        this.name = name;
        this.images = images;
    }
    public boolean isDisplaying() {
        return isDisplaying;
    }

    public void setDisplaying(boolean displaying) {
        isDisplaying = displaying;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
