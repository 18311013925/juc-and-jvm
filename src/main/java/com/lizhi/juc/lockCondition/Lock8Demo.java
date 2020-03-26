package com.lizhi.juc.lockCondition;

import java.util.concurrent.TimeUnit;

/**
 * @author: lizhi
 * @Date: 2020/3/20 11:32
 * @Description: 8锁
 */

class Phone {
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发送邮件");
    }

    public static synchronized void sendSms() throws Exception {
        System.out.println("发送短信");
    }

    public  void sayHello(){
        System.out.println("hello");
    }

}

/**
 * 标准访问， 请问先打印邮件还是短信? 邮件 -> 短信
 * 邮件暂停四秒钟， 先打印邮件还是短信？邮件 -> 短信
 * 两部手机，先打印啥
 * 两个静态同步方法，打印什么
 * 两部手机，  先打印邮件还是短信？
 */
public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "a").start();

        try{
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                phone2.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "b").start();

        new Thread(() -> {
            phone2.sayHello();
        }, "threadName").start();

    }
}
