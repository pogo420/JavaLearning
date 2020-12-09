package com.learning.java;

interface A{
    void checkA();
}

interface B{
    void checkB();
}

interface C{
    void checkC();
}

interface Mer extends A,B,C {
    void checkMer();
}

public class InterfaceScribbles implements Mer{
    @Override
    public void checkA() {
        System.out.println("A");
    }

    @Override
    public void checkB() {
        System.out.println("B");
    }

    @Override
    public void checkC() {
        System.out.println("C");
    }

    @Override
    public void checkMer() {
        System.out.println("Mer");
    }

    public static void main(String[] args) {
        
    }
}
