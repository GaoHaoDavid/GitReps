package com.xt.prototype;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep sheep=new Sheep(1,"多利","内蒙古");
        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        System.out.println(sheep1);
        System.out.println(sheep2);
        System.out.println(sheep3);
    }
}
