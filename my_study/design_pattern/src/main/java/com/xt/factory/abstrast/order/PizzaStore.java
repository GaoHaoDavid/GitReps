package com.xt.factory.abstrast.order;

import com.xt.factory.abstrast.factory.BJFactory;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
    }
}
