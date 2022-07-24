package com.xt.builder.improve;


public class HighHouse extends HouseBuilder{

    @Override
    public void buildBase() {
        System.out.println("给高楼打地基...");
    }

    @Override
    public void buildWalls() {
        System.out.println("给高楼打砌墙...");
    }

    @Override
    public void roofed() {
        System.out.println("给高楼封顶...");
    }
}
