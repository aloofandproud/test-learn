package com.immoc.diners.controller;

import cn.hutool.core.util.IdUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public  final static  List<String> list = new ArrayList<>();
    public static ExecutorService service = Executors.newFixedThreadPool(2000);
    public static void main(String[] args) {
//          List<String> list = new ArrayList<>();
//         ExecutorService service = Executors.newFixedThreadPool(2000);
        int nums = 0;
        for (int i = 0; i < 1000; i++) {
            service.submit(new Task2());
            nums++;
        }
        service.shutdown();

        while(!service.isTerminated()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        List<String> list = list;
        System.out.println("atomicInteger---"+atomicInteger.get());
        System.out.println("总共创建了 " + nums);

        System.out.println("原始大小：" + list.size());

        Set<String> set = new HashSet<>(list);
        System.out.println("去重以后的大小：" + set.size());


        System.out.println("over");
    }



    /**
     * @author Avery on 2022/3/16
     */

public static Lock lock = new ReentrantLock();

        public void test() {
//            long l = IdUtil.getSnowflake();
            String orderNo = IdUtil.getSnowflake(1, 1).nextIdStr();
//            System.out.println(orderNo);
            String num = getNum(orderNo);
            atomicInteger.getAndIncrement();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
//                lock.lock();
            synchronized (Test.class){
                    list.add(num);
            }
            }finally {
//                lock.unlock();
            }

        }


        private String getNum(String num) {

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String num1 = num + "xxx";

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return num1;
        }
public void testx(){

}
public void r(){

        }




}

class Task3 implements Runnable{

    @Override
    public void run() {
        new Test().test();
    }
}
