package com.company;

public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {

        final ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("main-thread : Hello");

        Thread thread = new Thread(() -> {
            // 获取不到主线程设置的值，所以为null
            System.out.println(threadLocal.get());
            threadLocal.set("sub-thread : World");
            System.out.println(threadLocal.get());
        });
        // 启动子线程
        thread.start();
        // 让子线程先执行完成，再继续执行主线
        thread.join();
        // 获取到的是主线程设置的值，而不是子线程设置的
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }
}
