package com.xt.builder;

public class HighHouse extends AbstractHouse{
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
