package com.xt.single;

/**
 * @Description: 静态内部类
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class Singleton7 {

    //1.私有化构造器
    private Singleton7(){}

    //2.静态内部类：(1)外部内被装载时，不会被立即装载。(2)当被调用时，才会被装载。
    private static class SingleInstance{
        //静态变量
        private static final Singleton7 instance=new Singleton7();
    }

    //3.提供一个公有的静态方法，实例化一个对象并返回
    public static Singleton7 getInstance(){
        return SingleInstance.instance;
    }

}
