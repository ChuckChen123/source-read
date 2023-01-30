package org.apache.dubbo.learn.test.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.junit.jupiter.api.Test;

public class Main {

    @Test
    public void testGetExtensionLoader() {
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);
    }

    @Test
    public void testGetDefaultExtension() {
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);
        extensionLoader.getDefaultExtension();
    }

    @Test
    public void testGetExtensionWithWrapper() {
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);
        Person person = extensionLoader.getExtension("g", true);
        System.out.println(person.sayHi());
    }

    @Test
    public void testGetExtensionWithOutWrapper() {
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);
        Person person = extensionLoader.getExtension("g", false);
        System.out.println(person.sayHi());
    }

    @Test
    public void testGetAdaptiveExtension() {
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);
        Person person = extensionLoader.getAdaptiveExtension();
        person.testAdaptive(URL.valueOf(""));
    }
}