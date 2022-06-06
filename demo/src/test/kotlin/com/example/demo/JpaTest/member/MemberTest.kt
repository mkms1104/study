package com.example.demo.JpaTest.member

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.annotation.Rollback

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class MemberTest {
    @Autowired private lateinit var memberRepository: MemberRepository

    @Test
    fun create() {
        val member = Member("mskim")
        memberRepository.save(member)
    }

    @Test
    fun update() {

    }

    @Test
    fun delete() {

    }

    @Test
    fun findOne() {
        this.create()
        val findById = memberRepository.findById(1)
        Assertions.assertTrue(findById.isPresent)
    }

    @Test
    fun findAll() {

    }
}