package com.org.proxyPattern.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKMeipo implements InvocationHandler {

    private Object target;

    public Object getInstance(Object person){
        this.target = person;
        Class<?> clazz = target.getClass();
        Object object = Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
        return object;


//        this.target = person;
//        Class<?> clazz = target.getClass();
//        Object object = Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
//        return object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(target,args);
        after();
        return object;
    }


    private void before(){
        System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
        System.out.println("开始物色");
    }

    private void after(){
        System.out.println("OK的话，准备办事");
    }
}
