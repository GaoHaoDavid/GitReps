package com.xt.net_thread.thread;

public class Thread_02 {

    public static void main(String[] args) {
        //实现Runnable接口的类无法直接调用start()方法去创建线程，需要借助Thread类去启动。
        Cat cat=new Cat();
        //静态代理
        Thread thread=new Thread(cat);
        thread.start();
    }

}

class Cat implements Runnable{

    int count;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第"+(++count)+"次，Thread_Name:"+Thread.currentThread().getName());
            if(count==8)
                break;
        }
    }
}
