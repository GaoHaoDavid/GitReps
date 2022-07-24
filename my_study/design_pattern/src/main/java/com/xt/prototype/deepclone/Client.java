package com.xt.prototype.deepclone;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        DeepPrototype deepPrototype=new DeepPrototype();
        deepPrototype.name="David";
        deepPrototype.deepCloneableTarget=new DeepCloneableTarget("DavidTarget","People");

        DeepPrototype deepPrototype1 = (DeepPrototype) deepPrototype.clone();
        System.out.println(deepPrototype);
        System.out.println(deepPrototype.deepCloneableTarget.hashCode());
        System.out.println(deepPrototype1);
        System.out.println(deepPrototype1.deepCloneableTarget.hashCode());

        DeepPrototype deepPrototype2 = (DeepPrototype) deepPrototype.deepClone();
        System.out.println(deepPrototype2);
        System.out.println(deepPrototype2.deepCloneableTarget.hashCode());
    }
}
