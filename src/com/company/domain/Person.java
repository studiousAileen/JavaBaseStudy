package com.company.domain;

public class Person {
    private String name;
    private int age;
    public  String address;
    public int id;
    public Person(){}

    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }


    public  void eat(){
        System.out.println("eat....");
    }
    public  void eat(String food){
        System.out.println("eat...."+food);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
