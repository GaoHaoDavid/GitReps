package com.xt.single;

/**
 * @Description: 枚举实现单例模式
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public enum Singleton8 {
    INSTANCE;

    public void test(){
        System.out.println("根据枚举在JVM唯一的特性，实现单例模式！");
    }
}
