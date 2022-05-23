package com.ct.test.bean;

public class User {

    int no;
    String name;
    String sex;
    int age;
    String birth;

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getBirth() {
        return birth;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public User() {
    }

    public User(int no, String name, String sex, int age, String birth) {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                '}';
    }
}
