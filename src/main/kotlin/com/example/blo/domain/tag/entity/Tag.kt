package com.example.blo.domain.tag.entity

import com.example.blo.domain.account.entity.Account
import com.example.blo.env.TableNameEnv
import javax.persistence.*

@Entity(name = TableNameEnv.tagTable)
data class Tag(
    @Column(length = 30)
    val name: String,

    @ManyToOne
    @JoinColumn(name = TableNameEnv.accountTable)
    val firstUser: Account
) {
    var usedCount: Long = 0
        protected set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun addUsedCount() {
        this.usedCount++
    }
}