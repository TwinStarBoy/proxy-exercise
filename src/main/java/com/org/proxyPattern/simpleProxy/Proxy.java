package com.org.proxyPattern.simpleProxy;

public class Proxy implements Subject {

    private Subject subject;
    public Proxy (Subject subject){
        this.subject = subject;
    }
    public void request() {
        before();
        subject.request();
        after();
    }

    public void before(){
        System.out.println("before service is called");
    }

    public void after(){
        System.out.println("after service is called");
    }
}
