package com.org.proxyPattern.staticProxy;

import com.org.proxyPattern.Person;

public class Son implements Person {
    public int findLove() {
        System.out.println("儿子要求:肤白貌美大肠腿");
        return 0;
    }
}
