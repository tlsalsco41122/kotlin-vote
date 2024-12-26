package com.manchae.kotlin_vote.domain.vote.domain.entity

import jakarta.persistence.*

@Entity
class VoteEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val choice: Boolean
)