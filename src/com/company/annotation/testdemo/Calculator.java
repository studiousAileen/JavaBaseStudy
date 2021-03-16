package com.company.annotation.testdemo;

public class Calculator {
    @Check
    public void add(){//加
        System.out.println("1 + 0 ="+(1+0));
    }

    @Check
    public void sub(){//减
        System.out.println("1 - 0 ="+(1-0));
    }

    @Check
    public void mul(){//乘
        System.out.println("1 * 0 ="+(1*0));
    }

    @Check
    public void div(){//除
        System.out.println("1 / 0 ="+(1/0));
    }

}
