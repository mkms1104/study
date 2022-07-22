package com.tobi.ch06.pointcutExpression;

public interface TargetInterface {
    void hello();
    void hello(String a);
    int minus(int a, int b) throws RuntimeException;
    int plus(int a, int b);
    void method();
}
