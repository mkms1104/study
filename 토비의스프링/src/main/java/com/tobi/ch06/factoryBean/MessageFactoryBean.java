package com.tobi.ch06.factoryBean;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Setter
public class MessageFactoryBean implements FactoryBean<Message> {
    String text;

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(text);
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
