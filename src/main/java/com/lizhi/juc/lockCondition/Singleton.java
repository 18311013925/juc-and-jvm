package com.lizhi.juc.lockCondition;

/**
 * @author: lizhi
 * @Date: 2020/3/26 10:19
 * @Description:
 * 线程安全的单利
 */
public class Singleton {

//    禁止指令重排，
//    private volatile static Singleton instance = null;
//    private Singleton() {
//    }
//
//    public static Singleton getInstance (){
//        if (instance == null) {
//           synchronized (Singleton.class){
//               if (instance == null) {
//                   instance = new Singleton();
//               }
//           }
//        }
//        return instance;
//    }

    // 基于类初始化的解决方案， 利用初始化instance时只能有一个线程。jvm 在类初始化阶段会获取一个锁，这个锁可以同步多个线程对同一个类的初始化
    public static class SingletonHolder{
        public static Singleton singleton = new Singleton();
    }
    public static Singleton getinstance(){
        return SingletonHolder.singleton;
    }
}
