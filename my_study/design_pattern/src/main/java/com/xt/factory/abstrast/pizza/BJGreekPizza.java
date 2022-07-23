package com.xt.factory.abstrast.pizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class BJGreekPizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京的胡椒披萨");
        System.out.println("准备"+name+"原材料");
    }
}
