package org.apache.dubbo.learn;

public class Boy implements Person {

    private Person person;

    public Boy(Person person) {
        this.person = person;
    }

    @Override
    public String sayHi() {
        return "我是李雷, " + person.sayHi();
    }
}
