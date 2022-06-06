package com.example.demo.redisTest

import org.springframework.data.repository.CrudRepository

interface RedisMemberRepository: CrudRepository<RedisMember, String> {
}