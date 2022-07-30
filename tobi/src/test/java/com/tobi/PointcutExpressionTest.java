package com.tobi;

import com.tobi.ch06.dynamicProxy.Hello;
import com.tobi.ch06.pointcutExpression.Target;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import static org.junit.jupiter.api.Assertions.*;

public class PointcutExpressionTest {

    @Test
    public void methodSignaturePointcut() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public int com.tobi.ch06.pointcutExpression.Target.minus(int, int) throws java.lang.RuntimeException)");

        assertTrue(pointcut.getClassFilter().matches(Target.class) && pointcut.getMethodMatcher().matches(Target.class.getMethod("minus", int.class, int.class), null));

        assertFalse(pointcut.getClassFilter().matches(Target.class) && pointcut.getMethodMatcher().matches(Target.class.getMethod("plus", int.class, int.class), null));

        assertFalse(pointcut.getClassFilter().matches(Hello.class));
    }
}
