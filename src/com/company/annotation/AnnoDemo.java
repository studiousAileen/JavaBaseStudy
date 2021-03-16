package com.company.annotation;
@SuppressWarnings("all")
@MyAnno(name = "zhangsan")
public class AnnoDemo {
    @Override
    public String toString() {
        return super.toString();
    }
    @Deprecated
    public void show1() {
        //有缺陷
    }
    public void show2() {
        //替代show1
    }
    public void demo() {
        show1();
    }

}
