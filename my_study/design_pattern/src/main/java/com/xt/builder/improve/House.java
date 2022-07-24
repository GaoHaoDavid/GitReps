package com.xt.builder.improve;

public class House {

    private String baise;
    private String walls;
    private String roofed;

    public String getBaise() {
        return baise;
    }

    public void setBaise(String baise) {
        this.baise = baise;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getRoofed() {
        return roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }

    public House() {
    }

    public House(String baise, String walls, String roofed) {
        this.baise = baise;
        this.walls = walls;
        this.roofed = roofed;
    }

    @Override
    public String toString() {
        return "House{" +
                "baise='" + baise + '\'' +
                ", walls='" + walls + '\'' +
                ", roofed='" + roofed + '\'' +
                '}';
    }
}
