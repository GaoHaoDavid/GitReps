package com.xt.factory.method.order;

import com.xt.factory.abstrast.pizza.BJCheesePizza;
import com.xt.factory.method.pizza.BJGreekPizza;
import com.xt.factory.abstrast.pizza.Pizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class BJOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if(orderType.equals("cheese")){
            pizza=new BJCheesePizza();
        }else if(orderType.equals("greek")){
            pizza=new BJGreekPizza();
        }
        return pizza;
    }
}
