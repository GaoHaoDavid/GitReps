package com.xt.net_thread.thread.ticket;

public class Ticket extends Thread{

    private static int count=100;

    private boolean flag=true;

    Object object=new Object();

    // 1.锁的是this
    // public synchronized void sell() 的用法同  synchronized (this)
    public void sell(){
      // synchronized (this) 同 synchronized (object)（同一对象）
        synchronized (object){
            if(count<=0){
                System.out.println("票已售完！");
                flag=false;
                return;
            }
            try {
                Thread.sleep(50);
                System.out.println("ThreadName="+Thread.currentThread().getName()+",还剩下"+(--count)+"张票!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //  2.锁的是.Class
    //  public synchronized static void s1() 同  public static void s2()
    public synchronized static void s1(){
        System.out.println("静态同步代码块。。。");
    }

    public static void s2(){
        synchronized (Ticket.class){
            System.out.println("静态同步代码块。。。");
        }
    }

    @Override
    public void run() {
        while (flag){
            sell();
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
