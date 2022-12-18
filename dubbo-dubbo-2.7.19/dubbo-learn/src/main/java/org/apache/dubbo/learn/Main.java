package org.apache.dubbo.learn;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class Main {
    public static void main(String[] args) {
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);
        Person person = extensionLoader.getDefaultExtension();
        System.out.println(person.sayHi());
    }
}