package com.example.demo.transaction

import com.example.demo.jpa.Member
import com.example.demo.jpa.MemberRepository
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class RedisJpaTxServiceTest(
    val redisTemplate: RedisTemplate<String, Any>,
    val memberRepository: MemberRepository,
    val txService: RedisJpaTxService
) {

    @BeforeEach
    fun redisFlushAll() {
        redisTemplate.connectionFactory?.connection?.flushAll()
    }

    @Test
    @DisplayName("레디스 트랜잭션 롤백 테스트")
    fun redisRollback() {
        assertThrows(RuntimeException::class.java) {
            txService.taskGeneratorInTx(true) {
                redisTemplate.opsForValue().set("name", "mskim")
                redisTemplate.opsForValue().set("age", 29)
            }
        }

        val opsForValue = redisTemplate.opsForValue()
        assertNull(opsForValue.get("name"))
        assertNull(opsForValue.get("age"))
    }

    @Test
    @DisplayName("레디스, JPA 통합 롤백 테스트")
    fun allRollback() {
        // 서비스 호출 시 예외 발생
        assertThrows(RuntimeException::class.java) {
            txService.taskGeneratorInTx(true) {
                redisTemplate.opsForValue().set("name", "mskim")
                redisTemplate.opsForValue().set("age", 29)
                memberRepository.save(Member("mskim"))
                memberRepository.save(Member("민수"))
            }
        }

        // 롤백 되었으므로 Redis 데이터 없어야 한다.
        val opsForValue = redisTemplate.opsForValue()
        assertNull(opsForValue.get("name"))
        assertNull(opsForValue.get("age"))

        // 롤백 되었으므로 DB 데이터 없어야 한다.
        assertNull(memberRepository.findByName("mskim"))
        assertNull(memberRepository.findByName("민수"))
    }
}