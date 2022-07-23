package com.xt.factory.method.order;

import com.xt.factory.simple.order.OrderPizza;
import com.xt.factory.simple.order.SimpleFactory;

/**
 * @Description: 相当于订购披萨的客户端
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class PizzaStore {

    public static void main(String[] args) {
        String location="bj";
        if(location.equals("bj")){
            new BJOrderPizza();
        }if (location.equals("ld")){
            new LDOrderPizza();
        }
    }
}
