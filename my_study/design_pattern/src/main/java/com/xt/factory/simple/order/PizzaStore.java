package com.xt.factory.simple.order;

/**
 * @Description: 相当于订购披萨的客户端
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class PizzaStore {

    public static void main(String[] args) {
        //new OrderPizza();
        new OrderPizza(new SimpleFactory());
    }
}
