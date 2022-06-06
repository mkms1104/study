package com.example.demo.mapStruct

data class MessageDto(
    val to: String,
    val title: String,
    val body: String,
    val messageType: String
) {
}