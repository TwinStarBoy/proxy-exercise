package com.org.proxyPattern.dynamicProxy.gpProxy;

import java.lang.reflect.Method;

public class GPMeipo implements GPInvocationHandler {

    private Object target;

    public Object getInstance(Object person){
        this.target = person;
        Class<?> clazz = target.getClass();


    }
    public Object invoke(Object proxy, Method method, Object[] args) {
        return null;
    }
}
