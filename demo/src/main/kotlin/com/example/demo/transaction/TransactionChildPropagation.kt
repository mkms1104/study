package com.example.demo.transaction

import com.example.demo.jpa.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionChildPropagation(
    val memberRepository: MemberRepository
) {

    @Transactional(propagation = Propagation.NESTED)
    fun child() {
//        memberRepository.save(Member("child"))
//        throw RuntimeException()
    }
}