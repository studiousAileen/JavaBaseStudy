package com.company.reflect;

import com.company.domain.Person;

import java.lang.reflect.Method;

public class ReflectDemo4 {
    public static void main(String[] args) throws Exception {
        //1:获取指定方法
        Class personClass = Person.class;
        Method eat = personClass.getMethod("eat");
        Method eatfood = personClass.getMethod("eat",String.class);
        System.out.println(eat);
        //执行方法
        Person person = new Person();
        eat.invoke(person);
        eatfood.invoke(person,"面包");

        //获取所有public方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods){
            String name = method.getName();
            //System.out.println(method);
            //System.out.println(name);
        }

        String classname = personClass.getName();
        System.out.println("======"+classname);


    }
}
