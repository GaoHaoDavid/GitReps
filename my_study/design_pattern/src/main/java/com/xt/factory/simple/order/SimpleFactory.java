package com.xt.factory.simple.order;

import com.xt.factory.simple.pizza.CheesePizza;
import com.xt.factory.simple.pizza.ChinaPizza;
import com.xt.factory.simple.pizza.GreekPizza;
import com.xt.factory.simple.pizza.Pizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class SimpleFactory {

    public Pizza getPizza(String orderType){
        System.out.println("**********使用简单工厂模式创建对象**********");
        Pizza pizza=null;
        if(orderType.equals("greek")){
            pizza=new GreekPizza();
            pizza.setName("希腊披萨");
        } else if(orderType.equals("cheese")){
            pizza=new CheesePizza();
            pizza.setName("奶酪披萨");
        }else if(orderType.equals("china")){
            pizza=new ChinaPizza();
            pizza.setName("奶酪披萨");
        }
        return pizza;
    }
}
