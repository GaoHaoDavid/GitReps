package com.xt.factory.simple.pizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class GreekPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("给希腊披萨准备原材料...");
    }
}
