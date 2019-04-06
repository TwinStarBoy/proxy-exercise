package com.org.proxyPattern.dynamicProxy.cglibProxy;

public class CglibTest {
    public static void main(String[] args) {
        Customer obj = (Customer) new CGLibMeipo().getInstance(Customer.class);

        System.out.println(obj);

        obj.findLove();
    }
}
