package com.tobi;

import com.tobi.ch06.dynamicProxy.Hello;
import com.tobi.ch06.dynamicProxy.HelloTarget;
import com.tobi.ch06.dynamicProxy.HelloUppercase;
import com.tobi.ch06.dynamicProxy.UppercaseHandler;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicProxyTest {

    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertEquals("Hello Toby", hello.sayHello("Toby"));
        assertEquals("Hi Toby", hello.sayHi("Toby"));
        assertEquals("Thank You Toby", hello.sayThankYou("Toby"));
    }

    @Test
    public void helloUppercase() {
        Hello proxyHello = new HelloUppercase(new HelloTarget());

        assertEquals("HELLO TOBY", proxyHello.sayHello("Toby"));
        assertEquals("HI TOBY", proxyHello.sayHi("Toby"));
        assertEquals("THANK YOU TOBY", proxyHello.sayThankYou("Toby"));
    }

    @Test
    public void dynamicProxy() {
        Hello proxyHello = (Hello) Proxy.newProxyInstance(
                                        getClass().getClassLoader(),
                                        new Class[] {Hello.class},
                                        new UppercaseHandler(new HelloTarget())
                                    );

        assertEquals("HELLO TOBY", proxyHello.sayHello("Toby"));
        assertEquals("HI TOBY", proxyHello.sayHi("Toby"));
        assertEquals("THANK YOU TOBY", proxyHello.sayThankYou("Toby"));
    }
}
