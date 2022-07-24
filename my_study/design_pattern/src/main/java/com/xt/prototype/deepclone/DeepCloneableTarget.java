package com.xt.prototype.deepclone;

import java.io.Serializable;

public class DeepCloneableTarget implements Serializable,Cloneable {

    private static final Long serialVersionID=1L;

    private String cloneName;

    private String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    @Override
    public String toString() {
        return "DeepCloneableTarget{" +
                "cloneName='" + cloneName + '\'' +
                ", cloneClass='" + cloneClass + '\'' +
                '}';
    }

    /**
     * 该类的属性都是String，直接使用默认的clone方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
