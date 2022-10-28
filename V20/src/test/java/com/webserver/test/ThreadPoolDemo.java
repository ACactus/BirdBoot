package com.webserver.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/22 20:34
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //获取一个内含两个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        //创建五个线程，将他们分别加入到线程池中
        for (int  i = 1; i < 5; i++) {
            //定义一个匿名类写入我们要执行的任务
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Thread current = Thread.currentThread();
                    System.out.println(current.getName() + "开始运行了！");
                    try {
                        //模拟执行业务
                        current.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(current.getName() + "结束了");
                }
            };
            //将任务加入到线程池中等待被执行
            threadPool.execute(task);
        }

        //结束线程:等待已开始的线程执行完毕，同时停止接收新的线程
        threadPool.shutdown();

        //立刻结束所有线程（正在被执行的线程也将被强行中断）
        //threadPool.shutdownNow();
    }
}
