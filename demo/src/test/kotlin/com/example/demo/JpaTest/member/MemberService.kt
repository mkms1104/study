package com.example.demo.JpaTest.member

import org.springframework.stereotype.Service
import javax.validation.ValidationException

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun print(id: Long) {
        val findById = memberRepository.findById(id)
        if(findById.isEmpty) throw ValidationException("사용자 정보가 존재하지 않습니다.")

        println("안녕하세요. 저는 ${findById.get().name()} 입니다.")
    }
}