package com.xt.builder;

public abstract class AbstractHouse {

    public abstract void buildBase();
    public abstract void buildWalls();
    public abstract void roofed();

    public void build(){
        buildBase();
        buildWalls();
        roofed();
    }
}
