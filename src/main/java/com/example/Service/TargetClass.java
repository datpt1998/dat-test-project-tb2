package com.example.Service;

public class TargetClass {
    private int a;
    private int b;
    private Integer aCanNull;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void getAdd() {
        System.out.println(a+b);
    }

    public Integer getaCanNull() {
        return aCanNull;
    }

    public void setaCanNull(Integer aCanNull) {
        this.aCanNull = aCanNull;
    }
}
