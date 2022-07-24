package com.xt.prototype;

public class Sheep implements Cloneable{

    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Sheep(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    //重写clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Sheep sheep=null;
        try {
            sheep= (Sheep) super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sheep;
    }
}
