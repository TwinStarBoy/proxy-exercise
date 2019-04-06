package com.org.proxyPattern.dynamicProxy.jdkProxy;

import com.org.proxyPattern.Person;

public class Girl implements Person {
    public int findLove() {
        System.out.println("高富帅");
        System.out.println("身高180cm");
        System.out.println("有6块腹肌");
        return 0;
    }
}
