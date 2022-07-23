package com.xt.single;

/**
 * @Description: 懒汉式（同步代码块）
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class Singleton5 {

    //1.私有化构造器
    private Singleton5(){}

    //2.静态变量
    private static Singleton5 instance;

    //3.提供一个公有的静态方法(在方法内部加同步锁)，实例化一个对象并返回
    public static Singleton5 getInstance(){
        if(instance==null){
            synchronized (Singleton5.class){
                instance=new Singleton5();
            }
        }
        return instance;
    }
}
