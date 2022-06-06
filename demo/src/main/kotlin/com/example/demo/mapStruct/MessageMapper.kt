package com.example.demo.mapStruct

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface MessageMapper {
    fun toMessageEntity(messageDto: MessageDto): MessageEntity
}