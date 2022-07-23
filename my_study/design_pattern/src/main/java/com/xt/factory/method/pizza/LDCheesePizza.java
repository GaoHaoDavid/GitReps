package com.xt.factory.method.pizza;

import com.xt.factory.abstrast.pizza.Pizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦的奶酪披萨");
        System.out.println("准备"+name+"原材料");
    }
}
