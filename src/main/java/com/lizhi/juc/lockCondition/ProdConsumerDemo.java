package com.lizhi.juc.lockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition {
    private int number = 0;

    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

   /* public void increment() throws Exception {

        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

//    老版本

   public synchronized void increment()throws Exception {
//        判断
        while (number != 0) {
            this.wait();
        }
//        干活
        number++;
//        通知
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

    public synchronized void decrement()throws Exception {
        if (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+ "\t" + number);
        this.notifyAll();
    }


}


/**
 * @author: lizhi
 * @Date: 2020/3/20 14:16
 * @Description: 生产者与消费者实现线程通信
 * 题目：现在两个线程， 可以操作初始值为零的一个变量
 * 实现一个线程对该变量加， 一个线程对该变量减
 * 实现交替，来10轮，变量初识值为零
 * <p>
 * 1  高内聚低耦合
 * 2  判断/唤醒/通知
 * 3  防止多线程虚假唤醒
 * <p>
 * 知识小结 = 多线程编程套路 + while判断 + 新版本写法
 */
public class ProdConsumerDemo {

    public static void main(String[] args) throws Exception {
        Aircondition aircondition = new Aircondition();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(100);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "a").start();


        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "b").start();


        // 两个生产，两个消费
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(300);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "c").start();


        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(400);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "d").start();
    }
}
