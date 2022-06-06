package com.example.demo.JpaTest.member

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(name: String) {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private var id: Long? = null

    private var name: String = name

    public fun name() = this.name

    override fun toString(): String {
        return "id = $id, name = $name"
    }
}