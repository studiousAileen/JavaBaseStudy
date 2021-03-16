package com.company.annotation.testdemo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestCheck {
    public static void main(String[] args) throws IOException {
            //1.创建计算器对象
            Calculator c = new Calculator();
            //2.获取字节码文件对象：期望获取所有的方法
            Class cls = c.getClass();
            //3.获取所有方法
            Method[] methods = cls.getMethods();
            int number = 0;//异常出现的次数
            BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));
            for (Method method : methods) {
                //判断方法是否有这个注解
                if (method.isAnnotationPresent(Check.class)){
                    //5.有就执行
                    try {
                        method.invoke(c);
                    } catch (Exception e) {
                        //6.捕获异常记录到文件中
                        number++;
                        bw.write(method.getName()+"方法出异常了");
                        bw.newLine();//换行
                        bw.write("异常的名称"+e.getCause().getClass().getSimpleName());
                        bw.newLine();
                        bw.write("异常的原因"+e.getCause().getMessage());
                        bw.newLine();
                        bw.write("---------------------");
                    }
                }
            }
            bw.write("本次测试一个出现"+number+"次异常");
            bw.flush();//刷新
            bw.close();
        }
}
