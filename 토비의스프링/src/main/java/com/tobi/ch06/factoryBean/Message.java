package com.tobi.ch06.factoryBean;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {
    String text;

    public static Message newMessage(String text) {
        return new Message(text);
    }
}
