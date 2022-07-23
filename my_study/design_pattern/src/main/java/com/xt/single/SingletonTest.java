package com.xt.single;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton8 instance1 = Singleton8.INSTANCE;
        Singleton8 instance2 = Singleton8.INSTANCE;
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1==instance2);
    }
}
