package com.example.demo.JpaTest.member

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
internal class MemberServiceTest {
    @Autowired private lateinit var memberService: MemberService
    @MockBean private lateinit var memberRepository: MemberRepository

    @Test
    fun print(){
        val id = 1L
        given(memberRepository.findById(id)).willReturn(Optional.of(Member("mskim")))
        given(memberRepository.findById(2)).willReturn(Optional.of(Member("mskim22")))

        memberService.print(id)

        val memberOptional = memberRepository.findById(2)
        println(memberOptional.get())
    }
}