package com.example.cube;

public class Image {
    private int id;
    private String name;
    private String leds_status;
    public Image(int id, String name, String leds_status) {
        this.id = id;
        this.name = name;
        this.leds_status = leds_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeds_status() {
        return leds_status;
    }

    public void setLeds_status(String leds_status) {
        this.leds_status = leds_status;
    }


}
