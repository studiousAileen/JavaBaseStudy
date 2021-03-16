package com.company.reflect;

import com.company.domain.Person;

import java.lang.reflect.Field;

public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
        //1:获取Person的class对象
        Class personClass = Person.class;
        Field[] fields = personClass.getFields();
        for(Field field: fields){
            System.out.println(field);
        }
        System.out.println("===================");
        //String address = "aaaa";
        Field address = personClass.getField("address");
        System.out.println(address);
        //获取成员变量address的值
        Person person = new Person();
        Object value = address.get(person);
        System.out.println(value);
        //设置成员变量address的值
        address.set(person,"henan");
        System.out.println(person);

        //Field[]getDeclaredFields():获取所有的成员变量，不考虑修饰符
        System.out.println("===================");
        Field[] declaredFields = personClass.getDeclaredFields();
        for(Field field: declaredFields){
            System.out.println(field);
        }
        //Field getDeclaredField(String name)
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);  // 暴力反射
        Object nameval = name.get(person);
        System.out.println(name);
        System.out.println(nameval);
        Person person2 = new Person();
        Object value2 = name.get(person2);
        //name 是private name.get(person2)需要忽略访问权限修饰符的安全检查
        name.setAccessible(true);  // 暴力反射
        System.out.println(value2);
        //设置成员变量address的值
        name.set(person,"weilin");
        System.out.println(person);

    }
}
