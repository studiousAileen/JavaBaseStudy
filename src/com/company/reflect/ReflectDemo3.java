package com.company.reflect;

import com.company.domain.Person;

import java.lang.reflect.Constructor;

public class ReflectDemo3 {
    public static void main(String[] args) throws Exception {
        //1:获取Person的class对象
        Class personClass = Person.class;
        Constructor constructor = personClass.getConstructor(String.class,int.class);
        System.out.println(constructor);
        //创建对象
        Object person1 = constructor.newInstance("张三",23);
        System.out.println(person1);

    }
}
