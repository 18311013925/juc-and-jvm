package com.lizhi.juc.lockCondition;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "callable";
    }
}

/**
 * @author: lizhi
 * @Date: 2020/3/20 17:00
 * @Description:
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<String> future = new FutureTask(new MyThread());
        new Thread(future).start();
        String str = future.get();
        System.out.println(str);
    }

}
