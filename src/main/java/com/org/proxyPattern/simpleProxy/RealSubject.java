package com.org.proxyPattern.simpleProxy;

public class RealSubject implements Subject {
    public void request() {
        System.out.println("real service is called");
    }
}
