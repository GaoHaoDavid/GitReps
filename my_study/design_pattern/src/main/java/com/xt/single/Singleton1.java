package com.xt.single;

/**
 * @Description: 饿汉式（静态常量）
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class Singleton1 {

    //1.私有化构造器
    private Singleton1(){}

    //2.静态变量，类初始化时自从创建一个实例
    private static final Singleton1 instance=new Singleton1();

    //3.提供一个公有的静态方法，返回实例对象
    public static Singleton1 getInstance(){
        return instance;
    }
}
