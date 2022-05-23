package com.xt.net_thread.thread;

public class DeadLockDemo extends Thread{

    public static void main(String[] args) {
        DeadLock A=new DeadLock(true);
        DeadLock B=new DeadLock(false);
        A.setName("A线程");
        B.setName("B线程");
        A.start();
        B.start();
    }
}

class DeadLock extends Thread{

    static Object obj1=new Object();
    static Object obj2=new Object();

    boolean flag;

    public DeadLock(boolean flag){
        this.flag=flag;
    }

    @Override
    public void run() {
        if(flag){
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName()+"进入1！");
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+"进入2！");
                }
            }
        }else{
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName()+"进入3！");
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName()+"进入4！");
                }
            }
        }
    }
}
