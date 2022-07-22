package com.tobi;

import com.tobi.ch06.dynamicProxy.Hello;
import com.tobi.ch06.dynamicProxy.HelloTarget;
import com.tobi.ch06.dynamicProxy.HelloUppercase;
import com.tobi.ch06.dynamicProxy.UppercaseHandler;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

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

    @Test
    public void proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello) proxyFactoryBean.getObject();

        assertEquals("HELLO TOBY", proxiedHello.sayHello("Toby"));
        assertEquals("HI TOBY", proxiedHello.sayHi("Toby"));
        assertEquals("Thank You Toby", proxiedHello.sayThankYou("Toby"));
    }

    static class UppercaseAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            String ret = (String) invocation.proceed();
            return ret.toUpperCase();
        }
    }
}
