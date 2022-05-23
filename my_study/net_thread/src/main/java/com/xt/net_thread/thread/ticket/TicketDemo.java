package com.xt.net_thread.thread.ticket;



public class TicketDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket).start();
        new Thread(ticket).start();
        new Thread(ticket).start();

        //三个线程同时售票，会出现票多售出的问题。
        //解决方法1：Ticket类中设置一个变量控制售票，主线程通过设置该变量控制子线程的执行。
        //解决方法2：给售票方法上同步锁
//        ticket1.setFlag(false);
//        ticket1.interrupt();
    }
}
