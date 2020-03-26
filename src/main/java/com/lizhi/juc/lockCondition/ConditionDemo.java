package com.lizhi.juc.lockCondition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程操作资源类
 *
 * 判断， 操作，通知
 *
 * 注意， condition 等待方法是 await 而不是wait
 */
class ShareData{
    private int number = 1;  // A:1 B:2 C:3

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
//             判断
            while (number != 1) {
                c1.await();
            }
//            操作
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+ number );
            }
//            通知， 唤醒
//            必须先修改标识位
            number = 2;
//            如何通知第二个  this.notify(); 不合适
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
//             判断
            while (number != 2) {
                c2.await();
            }
//            操作
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+ number );
            }
//            通知， 唤醒
//            必须先修改标识位
            number = 3;
//            如何通知第二个  this.notify(); 不合适
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
//             判断
            while (number != 3) {
                c3.await();
            }
//            操作
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+ number);
            }
//            通知， 唤醒
//            必须先修改标识位
            number = 1;
//            如何通知第二个  this.notify(); 不合适
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
/**
 * @author: lizhi
 * @Date: 2020/3/20 15:58
 * @Description: 题目
 * 多线程之间按顺序调用 a -> b -> c
 *三个线程启动，要求如下：
 * AA打印5次， BB打印10次，CC打印15次，
 * 接着
 * AA打印5次， BB打印10次，CC打印15次，
 * 来10轮
 *
 * java.lang.IllegalMonitorStateException 非法监控异常
 * java.lang.ConcurrentModificationException  并发修改异常
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ShareData data = new ShareData();

        new Thread(() -> { for (int i = 0; i < 10; i++) { data.print5(); } }, "A").start();

        new Thread(() -> { for (int i = 0; i < 10; i++) { data.print10(); }}, "B").start();

        new Thread(() -> { for (int i = 0; i < 10; i++) { data.print15(); } }, "C").start();
    }


}
