package com.manchae.kotlin_vote.domain.room.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_room")
class RoomEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val content: String
)