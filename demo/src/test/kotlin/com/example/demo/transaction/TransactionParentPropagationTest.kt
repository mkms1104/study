package com.example.demo.transaction

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class TransactionParentPropagationTest(
    val transactionParentPropagation: TransactionParentPropagation,
) {

    @Test
    fun run() {
        transactionParentPropagation.parent()
    }
}