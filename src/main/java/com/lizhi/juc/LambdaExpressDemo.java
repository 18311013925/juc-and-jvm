package com.lizhi.juc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 1、函数式编程
 * 口诀：拷贝小括号 ， 写死右箭头， 落地大括号
 *
 * lambda 表达式就是解决内部类
 */

interface Foo{
    void sayHello();
}
@FunctionalInterface
interface Foo2{
    int add(int x , int y);
    default void sayHello(){
        System.out.println("hello");
    }
}

public class LambdaExpressDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread("threadName").start();
        }
        Map map = new LinkedHashMap<>();

    }

    /*
    * lambda 学习
    * */
    private static void lambdaExpressLearn() {
        /**
         * 匿名内部类，代码一坨
         */
        /*Foo foo = new Foo() {
            public void sayHello() {
                System.out.println("hello");
            }
        };
        foo.sayHello();*/
//        lambda
        Foo foo = () -> {
            System.out.println("hello");
        };
        foo.sayHello();
    }


}
