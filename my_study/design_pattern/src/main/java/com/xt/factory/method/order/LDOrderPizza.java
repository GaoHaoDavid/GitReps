package com.xt.factory.method.order;

import com.xt.factory.abstrast.pizza.Pizza;
import com.xt.factory.method.pizza.*;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class LDOrderPizza extends OrderPizza {

    @Override
    com.xt.factory.abstrast.pizza.Pizza createPizza(String orderType) {
        Pizza pizza=null;
        if(orderType.equals("cheese")){
            pizza=new LDCheesePizza();
        }else if(orderType.equals("greek")){
            pizza=new LDGreekPizza();
        }
        return pizza;
    }
}
