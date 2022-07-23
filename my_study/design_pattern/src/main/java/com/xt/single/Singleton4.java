package com.xt.single;

/**
 * @Description: 懒汉式（线程安全，同步方法）
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class Singleton4 {

    //1.私有化构造器
    private Singleton4(){}

    //2.静态变量
    private static Singleton4 instance;

    //3.提供一个公有的静态方法(加同步锁)，实例化一个对象并返回
    public static synchronized Singleton4 getInstance(){
        if(instance==null){
            instance=new Singleton4();
        }
        return instance;
    }
}
