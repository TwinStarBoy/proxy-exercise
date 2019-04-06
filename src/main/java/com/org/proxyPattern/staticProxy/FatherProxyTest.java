package com.org.proxyPattern.staticProxy;

import com.org.proxyPattern.Person;

public class FatherProxyTest {
    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }
}
