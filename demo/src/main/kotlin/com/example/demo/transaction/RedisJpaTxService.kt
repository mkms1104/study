package com.example.demo.transaction

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RedisJpaTxService {
    @Transactional
    fun taskGeneratorInTx(isException: Boolean, doTask: () -> Unit) {
        doTask()
        if(isException) throw RuntimeException("강제 예외 발생")
    }
}