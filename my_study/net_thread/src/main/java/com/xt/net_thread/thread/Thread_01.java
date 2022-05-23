package com.xt.net_thread.thread;

public class Thread_01 {

    public static void main(String[] args) {
        Dog dog=new Dog();
        //start()会启动线程
        dog.start();
        //设置dog线程为守护线程，当main线程执行完毕，dog线程就会自动结束。
//        dog.setDaemon(true);
        //子线程执行过程中，主线程main是不会阻塞的，主线程和子线程交替执行
        for (int i = 0; i <10 ; i++) {
            System.out.println("主线程："+i+",Thread_Name:"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

/**
 * 继承Thread类创建线程
 */
class Dog extends Thread{

    private int size;

    /**
     * 线程启动时会执行
     */
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第"+(++size)+"次，Thread_Name:"+Thread.currentThread().getName());
            if(size==8)
                break;
        }
    }
}
