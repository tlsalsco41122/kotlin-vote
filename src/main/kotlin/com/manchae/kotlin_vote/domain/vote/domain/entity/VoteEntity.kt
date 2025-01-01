package com.manchae.kotlin_vote.domain.vote.domain.entity

import com.manchae.kotlin_vote.domain.room.domain.entity.RoomEntity
import jakarta.persistence.*

@Entity
@Table(name = "tb_vote")
class VoteEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val choice: Boolean,

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    val room: RoomEntity
)