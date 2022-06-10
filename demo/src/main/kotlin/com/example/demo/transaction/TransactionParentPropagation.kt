package com.example.demo.transaction

import com.example.demo.jpa.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionParentPropagation(
    val memberRepository: MemberRepository,
    val transactionChildPropagation: TransactionChildPropagation
) {

    @Transactional
    fun parent() {
//        memberRepository.save(Member("parent"))
//        try {
            transactionChildPropagation.child()
        throw RuntimeException()
//        } catch (e: Exception) {
//        }
    }
}
