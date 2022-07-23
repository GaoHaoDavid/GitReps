package com.xt.factory.simple.pizza;

/**
 * @Description: 将pizza类做成一个抽象的
 * @Author: gaohao
 * @Date: Created in 2022/7/18
 */
public abstract class Pizza {

    //名称
    protected String name;

    //每种pizza需要准备的原材料不一样，因为做成抽象方法，子类去具体实现
    public abstract void prepare();

    public void bake(){
        System.out.println(name+"baking...");
    }

    public void cut(){
        System.out.println(name+"cutting...");
    }

    public void box(){
        System.out.println(name+"boxing...");
    }

    public void setName(String name){
        this.name=name;
    }
}
