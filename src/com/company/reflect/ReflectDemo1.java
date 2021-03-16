package com.company.reflect;

import com.company.domain.Person;
import com.company.domain.Student;

public class ReflectDemo1 {
    /**
     获取class对象的方式：
     1:Class.forName("全类名（包名.class）"):将字节码文件加载进内存，返回Class对象
     2:类名.class:通过类名的属性class获取
     3:对象.getClass():getClass()在Object类中定义
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class cls1 = Class.forName("com.company.domain.Person");
        System.out.println(cls1);
        Class cls2 = Person.class;
        System.out.println(cls2);
        Person person = new Person();
        Class cls3 = person.getClass();
        System.out.println(cls3);

        //验证三个对象是否是一个对象，即对象地址相同
        System.out.println(cls1 ==cls3);
        System.out.println(cls1 ==cls2);

        Student student = new Student();
        Class cls4 = student.getClass();
        System.out.println(cls1 == cls4);


    }

}
