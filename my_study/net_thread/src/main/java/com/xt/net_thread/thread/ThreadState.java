package com.xt.net_thread.thread;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        T t=new T();
        System.out.println(t.getName()+"线程的状态："+t.getState().name());
        t.start();
        while (Thread.State.TERMINATED!=t.getState()){
            System.out.println(t.getName()+"线程的状态："+t.getState().name());
            Thread.sleep(500);
        }
        System.out.println(t.getName()+"线程的状态："+t.getState().name());
    }
}

class T extends Thread{

    @Override
    public void run() {

        for (int i = 0; i < 10 ; i++) {
            System.out.println("第"+i+"次哦！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}