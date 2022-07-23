package com.xt.single;

/**
 * @Description: 懒汉式（线程不安全）
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class Singleton3 {

    //1.私有化构造器
    private Singleton3(){}

    //2.静态变量
    private static Singleton3 instance;

    //3.提供一个公有的静态方法，实例化一个对象并返回
    public static Singleton3 getInstance(){
        if(instance==null){
            instance=new Singleton3();
        }
        return instance;
    }
}
