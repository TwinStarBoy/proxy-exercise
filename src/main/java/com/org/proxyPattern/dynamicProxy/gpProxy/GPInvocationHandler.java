package com.org.proxyPattern.dynamicProxy.gpProxy;

import java.lang.reflect.Method;

public interface GPInvocationHandler {
    public Object invoke(Object proxy , Method method ,Object[] args);
}
