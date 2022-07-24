package com.xt.prototype.deepclone;

import java.io.*;

public class DeepPrototype implements Serializable,Cloneable {

    public String name;

    public DeepCloneableTarget deepCloneableTarget;

    public DeepPrototype(){
        super();
    }

    @Override
    public String toString() {
        return "DeepPrototype{" +
                "name='" + name + '\'' +
                ", deepCloneableTarget=" + deepCloneableTarget +
                '}';
    }

    //1.深拷贝，使用clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep=null;

        //只能完成对基本数据类型和String的拷贝
        deep = super.clone();
        //对引用类型单独处理
        DeepPrototype deepPrototype= (DeepPrototype) deep;
        deepPrototype.deepCloneableTarget= (DeepCloneableTarget) deepCloneableTarget.clone();

        return deepPrototype;
    }

    //2.深拷贝，使用对象的序列化实现（推荐）
    public Object deepClone() throws IOException {
        ByteArrayOutputStream bos=null;
        ObjectOutputStream oos=null;
        ByteArrayInputStream bis=null;
        ObjectInputStream ois=null;

        try{
            bos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(bos);
            oos.writeObject(this);

            bis=new ByteArrayInputStream(bos.toByteArray());
            ois=new ObjectInputStream(bis);
            DeepPrototype obj= (DeepPrototype) ois.readObject();
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bos.close();
            oos.close();
            bis.close();
            ois.close();
        }
        return null;
    }
}
