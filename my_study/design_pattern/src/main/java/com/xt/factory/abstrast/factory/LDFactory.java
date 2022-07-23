package com.xt.factory.abstrast.factory;

import com.xt.factory.abstrast.pizza.BJCheesePizza;
import com.xt.factory.abstrast.pizza.LDCheesePizza;
import com.xt.factory.abstrast.pizza.LDGreekPizza;
import com.xt.factory.abstrast.pizza.Pizza;
import com.xt.factory.method.pizza.BJGreekPizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class LDFactory implements AbstractFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if(orderType.equals("cheese")){
            pizza=new LDCheesePizza();
        }else if(orderType.equals("greek")){
            pizza=new LDGreekPizza();
        }
        return pizza;
    }
}
