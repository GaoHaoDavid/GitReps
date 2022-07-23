package com.xt.factory.abstrast.order;

import com.xt.factory.abstrast.factory.AbstractFactory;
import com.xt.factory.abstrast.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/19
 */
public class OrderPizza {

    private AbstractFactory factory;

    public OrderPizza(AbstractFactory factory){
        setFactory(factory);
    }

    public void setFactory(AbstractFactory factory){
        this.factory=factory;
        Pizza pizza=null;
        String orderType;
        do {
            orderType=getType();
            pizza=factory.createPizza(orderType);
            if(pizza!=null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("********订单结束********");
            }else if(orderType.equals("quit")){
                System.out.println("********退出程序********");
                break;
            }else {
                System.out.println("********订购失败********");
            }
        }while (true);

    }

    //获取用户订购的披萨种类
    public String getType(){
        BufferedReader br=null;
        String str="";
        try {
            br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入想订购的披萨种类：");
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
