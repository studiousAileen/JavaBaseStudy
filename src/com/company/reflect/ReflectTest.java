package com.company.reflect;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //可以创建任意类的对象，并且执行其中任意方法
        //在程序中加载读取配置文件,转换为一个集合
        Properties properties = new Properties();
        //获取class目录下的配置文件
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        properties.load(is);

        //获取配置文件中定义的数据
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        //使用反射技术来加载该类进内存
        Class cls = Class.forName(className);
        //创建对象
        Constructor constructor = cls.getConstructor();
        Object obj = constructor.newInstance();
        //获取方法
        Method method =cls.getMethod(methodName);
        //执行方法
        method.invoke(obj);

    }
}
