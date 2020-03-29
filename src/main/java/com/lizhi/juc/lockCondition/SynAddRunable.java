package com.lizhi.juc.lockCondition;

import java.util.concurrent.TimeUnit;

class Threaddead {
    static int a, b;

    public Threaddead(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void add() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(Thread.currentThread().getName() + ": " +( a + b));
            }
        }
    }


}

public class SynAddRunable {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                new Threaddead(1, 2).add();
            }, "A").start();
            new Thread(() -> {
                new Threaddead(1, 2).add();
            }, "B").start();
        }
    }

}
