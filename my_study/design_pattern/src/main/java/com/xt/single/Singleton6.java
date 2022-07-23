package com.xt.single;

/**
 * @Description: 双重检查
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class Singleton6 {

    //1.私有化构造器
    private Singleton6(){}

    //2.静态变量
    private static Singleton6 instance;

    //3.提供一个公有的静态方法(在方法内部加同步锁)，实例化一个对象并返回
    public static Singleton6 getInstance(){

        //提高性能在方法内加锁
        if(instance==null){
            synchronized (Singleton6.class){
                //双重检查
                if(instance==null){
                    instance=new Singleton6();
                }
            }
        }
        return instance;
    }
}
