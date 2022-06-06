package com.example.demo.mapStruct

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

internal class MessageMapperTest {

    @Test
    fun aaa() {
        val messageDto = MessageDto("aaa", "bbb", "ccc", "ddd")
        val messageMapper = Mappers.getMapper(MessageMapper::class.java)
        val toMessageEntity = messageMapper.toMessageEntity(messageDto)
        println(toMessageEntity)
    }
}