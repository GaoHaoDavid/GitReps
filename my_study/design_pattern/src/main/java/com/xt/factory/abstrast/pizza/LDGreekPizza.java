package com.xt.factory.abstrast.pizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class LDGreekPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦的胡椒披萨");
        System.out.println("准备"+name+"原材料");
    }
}
