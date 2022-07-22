package com.tobi;

import com.tobi.ch06.factoryBean.Message;
import com.tobi.ch06.factoryBean.MessageFactoryBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class FactoryBeanTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        assertTrue(message instanceof Message);
        assertEquals("Factory Bean", ((Message) message).getText());
    }

    @Test
    public void getFactoryBean() {
        Object factory = context.getBean("&message");
        assertTrue(factory instanceof MessageFactoryBean);
    }
}
