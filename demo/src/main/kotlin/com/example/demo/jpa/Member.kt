package com.example.demo.jpa

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(private var name: String) {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private var id: Long? = null

}