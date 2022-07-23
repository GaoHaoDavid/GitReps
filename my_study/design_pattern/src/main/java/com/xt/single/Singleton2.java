package com.xt.single;

/**
 * @Description: 饿汉式（静态代码块）
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class Singleton2 {

    //1.私有化构造器
    private Singleton2(){}

    //2.静态变量
    private static Singleton2 instance;

    //3.在静态代码块中创建对象实例
    static {
        instance=new Singleton2();
    }

    //4.提供一个公有的静态方法，返回实例对象
    public static Singleton2 getInstance(){
        return instance;
    }
}
