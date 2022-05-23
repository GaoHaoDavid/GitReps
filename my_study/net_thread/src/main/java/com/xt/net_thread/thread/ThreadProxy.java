package com.xt.net_thread.thread;

/**
 * 模拟了一个简单的Thread类。体现了静态代理去创建实现Runnable接口类的线程的思想。
 */
public class ThreadProxy implements Runnable{

    private Runnable target;

    @Override
    public void run() {
        if(target!=null)
            target.run();
    }

    public ThreadProxy(Runnable target){
        this.target=target;
    }

    public void start(){
        //真正创建线程的native方法
        start0();
    }

    private void start0() {
        run();
    }
}
