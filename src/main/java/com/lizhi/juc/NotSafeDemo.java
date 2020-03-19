package com.lizhi.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 不安全的集合
 * 记住这个异常 java.util.ConcurrentModificationException  并发修改异常
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        /**
         * 1、创建ArrayList对象做了什么？创建一个数组
         * 2、类型是什么？Object 类型
         * 3、初始化的空间是多少？ 默认是10
         * 4、扩容怎么扩   原值的一半
         * 5、扩容搬家用的是哪个方法？，Arrays.copyOf();
         * 6、第二次扩容从15 扩容到多少  22
         * 7、线程是否安全：否
         * 8、写一个线程不安全的 list
         *        1、故障现象
         *
         *        3、解决方法
         *        4、优化建议（同样的错误不范第二次）
         *  如果不使用Vector
         *  如果不使用  Collections.synchronizedList(new ArrayList<>());
         *  9、CopyOnWriteArrayList 写时复制技术 为什么是线程安全的？站撸远吗
         */
        List<Object> list = new CopyOnWriteArrayList<>(); // Collections.synchronizedList(new ArrayList<>()); //new Vector<>();  // new ArrayList<>();

        //多次运行结果不一样，数据不一致
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().subSequence(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
