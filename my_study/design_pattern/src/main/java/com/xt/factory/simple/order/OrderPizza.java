package com.xt.factory.simple.order;

import com.xt.factory.simple.pizza.CheesePizza;
import com.xt.factory.simple.pizza.GreekPizza;
import com.xt.factory.simple.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public class OrderPizza {

    private SimpleFactory simpleFactory;

    private Pizza pizza;

    public OrderPizza(SimpleFactory simpleFactory){
        setSimpleFactory(simpleFactory);
    }

    //使用简单工厂模式订购披萨
    public void setSimpleFactory(SimpleFactory simpleFactory){
        String orderType="";
        this.simpleFactory=simpleFactory;

        do{
            orderType=getType();
            pizza=this.simpleFactory.getPizza(orderType);
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

    //订购披萨
//    public OrderPizza(){
//        Pizza pizza=null;
//        String orderType;//订购披萨的类型
//        do{
//            orderType=getType();
//            if(orderType.equals("greek")){
//                pizza=new GreekPizza();
//                pizza.setName("希腊披萨");
//            } else if(orderType.equals("cheese")){
//                pizza=new CheesePizza();
//                pizza.setName("奶酪披萨");
//            }else {
//                break;
//            }
//            //输出pizza制作的过程
//            pizza.prepare();
//            pizza.bake();
//            pizza.cut();
//            pizza.box();
//            System.out.println("********订单结束********");
//        }while (true);
//    }

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
