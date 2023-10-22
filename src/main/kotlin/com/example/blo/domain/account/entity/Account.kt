package com.example.blo.domain.account.entity

import com.example.blo.global.security.auth.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class Account(
    name: String,
    accountId: String,
    password: String,
    role: Role,
    introduction: String? = null,
    id: Long? = null
) : UserDetails {
    @Column(length = 100)
    var name: String = name

    @Column(length = 200)
    var accountId: String = accountId // Authentication.name

    private var password: String = password

    @Column(length = 200)
    var introduction: String? = introduction

    @Column(length = 5)
    val role: Role = role

    var isBanned: Boolean = false

    var isDormant: Boolean = false

    var isDeleted: Boolean = false

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    val id: Long? = id

    override fun getPassword(): String = this.password
    fun setPassword(password: String) { this.password = password }

    override fun getUsername(): String = this.accountId
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(GrantedAuthority { this.role.string })

    override fun isCredentialsNonExpired(): Boolean = false
    override fun isAccountNonExpired(): Boolean = !this.isBanned && !this.isDeleted
    override fun isAccountNonLocked(): Boolean = !this.isBanned
    override fun isEnabled(): Boolean = !this.isDormant
}