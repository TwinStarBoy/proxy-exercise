package com.org.proxyPattern.staticProxy;

import com.org.proxyPattern.Person;

public class Father implements Person {

    private Person person;
    public Father (Person person){
        this.person = person;
    }
    public int findLove() {
        System.out.println("开始物色");
        person.findLove();
        System.out.println("双方同意,准备办事");
        return 0;
    }
}
