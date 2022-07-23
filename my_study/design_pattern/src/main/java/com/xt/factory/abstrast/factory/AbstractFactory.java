package com.xt.factory.abstrast.factory;

import com.xt.factory.abstrast.pizza.Pizza;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public interface AbstractFactory {

    Pizza createPizza(String orderType);
}
