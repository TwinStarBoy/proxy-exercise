package com.org.proxyPattern.dynamicProxy.jdkProxy;

import java.lang.reflect.Method;

public class JDKProxyTest {
    public static void main(String[] args) {
        try {
            Object obj = new JDKMeipo().getInstance(new Girl());
            System.out.println(obj);
            Method method = obj.getClass().getMethod("findLove",null);
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
