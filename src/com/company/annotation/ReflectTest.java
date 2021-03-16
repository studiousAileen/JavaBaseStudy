package com.company.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Pro(className = "com.company.annotation.Demo1",methoName = "show")
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //可以创建任意类的对象，并且执行其中任意方法
        //加完注解后，首先需要解析注解
        //1：获取该类的字节码文件对象
        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        //2:获取注解对象,其实就是在内存中生成类一个该注解接口的子类实现对象
        Pro pro = reflectTestClass.getAnnotation(Pro.class);
        /*
         相当于实现了这部分代码
         public class ProImpl extends Pro{
        @Override
        public String className() {
        return "com.company.annotation.Demo1";
        }
        @Override
        public String methoName() {
        return "show";
        }
        }
         */
        //3:调用注解对象中定义的抽象方法，获取返回值
        String className = pro.className();
        String methoName = pro.methoName();
        System.out.println(className);
        System.out.println(methoName);

        //使用反射技术来加载该类进内存
        Class cls = Class.forName(className);
        //创建对象
        Constructor constructor = cls.getConstructor();
        Object obj = constructor.newInstance();
        //获取方法
        Method method =cls.getMethod(methoName);
        //执行方法
        method.invoke(obj);


    }
}
