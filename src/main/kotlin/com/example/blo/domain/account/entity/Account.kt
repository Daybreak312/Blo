package com.example.blo.domain.account.entity

import com.example.blo.domain.blog.entity.Blog
import com.example.blo.env.TableNameEnv
import com.example.blo.global.base.entity.BaseTimeEntity
import com.example.blo.global.security.auth.Role
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE ${TableNameEnv.accountTable} SET is_deleted = true WHERE id = ?")
@Entity(name = TableNameEnv.accountTable)
class Account(
    name: String,
    accountId: String,
    password: String,
    role: Role,
    introduction: String = "",
    id: Long? = null
) : UserDetails, BaseTimeEntity() {

    @Column(length = 100)
    var name: String = name
        protected set

    @Column(unique = true, length = 200)
    var accountId: String = accountId
        protected set
    fun updateAccountId(accountId: String) {
        this.accountId = accountId
    }

    private var password: String = password
    override fun getPassword(): String = this.password
    fun updatePassword(encodedPassword: String) {
        this.password = encodedPassword
    }

    @Column(length = 200)
    var introduction: String = introduction
        protected set

    @Column(length = 5)
    val role: Role = role

    @OneToMany(mappedBy = "opener")
    val blogs: List<Blog> = arrayListOf()

    var isBanned: Boolean = false
        protected set
    fun banAccount() { this.isBanned = true }
    fun pardonAccount() { this.isBanned = false }

    var isDormant: Boolean = false
        protected set
    fun dormantAccount() { this.isDormant = true }
    fun activeAccount() { this.isDormant = false }

    var isDeleted: Boolean = false
        protected set
    fun deleteAccount() { this.isDeleted = true }
    fun restoreAccount() { this.isDeleted = false }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    val id: Long? = id

    override fun getUsername(): String = this.accountId
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(GrantedAuthority { this.role.name })

    override fun isCredentialsNonExpired(): Boolean = false
    override fun isAccountNonExpired(): Boolean = !this.isBanned && !this.isDeleted
    override fun isAccountNonLocked(): Boolean = !this.isBanned
    override fun isEnabled(): Boolean = !this.isDormant
}