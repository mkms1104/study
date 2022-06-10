package com.example.demo.redis

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.RedisSystemException
import org.springframework.data.redis.connection.RedisZSetCommands
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ScanOptions
import org.springframework.test.context.TestConstructor
import java.time.Duration

@DataRedisTest
@Import(RedisConfig::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RedisTemplateTest(
    private val redisTemplate: RedisTemplate<String, Any>
) {

//    @BeforeEach
//    fun afterTest() {
//        val keys = redisTemplate.keys("*")
//        keys.forEach { redisTemplate.delete(it) }
//    }

    @Test
    @DisplayName("opsForValue 동작 확인")
    fun opsForValue() {
        val opsForValue = redisTemplate.opsForValue()

        opsForValue.set("name", "mskim")
        opsForValue.set("name", "minsu")
        assertEquals("minsu", opsForValue.get("name"))

        redisTemplate.delete("name")
        assertNull(opsForValue.get("name"))

        opsForValue.append("name", "ms")
        opsForValue.append("name", "kim")
        assertEquals("mskim", opsForValue.get("name"))

        redisTemplate.delete("name")

        opsForValue.set("name", "1")
        opsForValue.increment("name")
        opsForValue.increment("name")
        opsForValue.increment("name")
        assertEquals("4", opsForValue.get("name"))

        redisTemplate.delete("name")

        opsForValue.set("name", "mskim")
        assertThrows(RedisSystemException::class.java) { opsForValue.increment("name") }

        redisTemplate.delete("name")

        opsForValue.setIfPresent("name", "mskim")
        opsForValue.setIfPresent("name", "minsu")
        assertNull(opsForValue.get("name"))

        opsForValue.setIfAbsent("name", "mskim")
        opsForValue.setIfAbsent("name", "minsu")
        assertEquals("mskim", opsForValue.get("name"))

        redisTemplate.delete("name")

        opsForValue.set("name", "mskim")
        opsForValue.getAndExpire("name", Duration.ofSeconds(10))
    }

    @Test
    fun opsForSet() {
        val ofs = redisTemplate.opsForSet()
        ofs.add("name", "mskim", "minsu", "mskim22")
        ofs.add("age", "10", "20", "mskim")

        assertTrue(ofs.difference("name", "age")!!.containsAll(setOf("minsu")))
        assertTrue(ofs.difference("age", "name")!!.containsAll(setOf("10", "20")))

        assertEquals(setOf("mskim"), ofs.intersect("age", "name"))

        val union = ofs.union("name", "age")
        println(union)

        val cursor = ofs.scan("name", ScanOptions.NONE)
        cursor.forEachRemaining { println(it) }
        cursor.close()

        println(ofs.members("age"))
        println(ofs.pop("age"))
        println(ofs.pop("age"))
        println(ofs.pop("age"))
        println(ofs.pop("age"))

        val move = ofs.move("nam", "minsu", "mskim")
        println(move)
        println(ofs.members("nam"))
        println(ofs.members("age"))
    }

    @Test
    fun opsForZSet() {
        val ofzs = redisTemplate.opsForZSet()
        ofzs.add("name", "mskim", 1.0)
        ofzs.add("name", "minsu", 2.1)
        ofzs.add("name", "ms", 2.0)

//        val range = ofzs.range("name", 1, 2)
//        println(range)
//
//        val rangeByScore = ofzs.rangeByScore("name", 1.0, 2.1)
//        println(rangeByScore)
//
//        println(ofzs.score("name", "minsu", "mskim"))
//
//        println(ofzs.rank("name", "minsu"))


        println(ofzs.zCard("name"))
        println(ofzs.size("name"))
        println(ofzs.count("name", 1.0, 2.1))
        println(ofzs.lexCount("name", RedisZSetCommands.Range.range().gt(2)))

        println(ofzs.reverseRank("name", "minsu"))
    }

    @Test
    fun opsForHash() {
        val ofh = redisTemplate.opsForHash<String, Any>()
        ofh.put("name", "className", "java")
        ofh.put("name", "className", "kotlin")
        ofh.put("name", "adName", "lotte")

        println(ofh.keys("name"))
        println(ofh.get("name", "className"))
        println(ofh.values("name"))
        println(ofh.entries("name"))
        println(ofh.randomKey("name"))
    }

    @Test
    fun opsForList() {
        val ofl = redisTemplate.opsForList()
        ofl.leftPush("name", "mskim")
        ofl.leftPush("name", "minsu")
        ofl.rightPush("name", "ms")

        println(ofl.range("name", 0, 100))
        println(ofl.index("name", 0))
        println(ofl.index("name", 1))

        println(ofl.indexOf("name", "ms"))

        ofl.set("name", 3, "zcbs")
        println(ofl.range("name", 0, 100))
    }
}