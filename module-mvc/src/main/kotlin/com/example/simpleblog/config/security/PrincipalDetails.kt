package com.example.simpleblog.config.security

import com.example.simpleblog.domain.member.Member
import com.fasterxml.jackson.annotation.JsonIgnore
import mu.KotlinLogging
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class PrincipalDetails(
    member: Member,
) : UserDetails {

    var member: Member = member
    private val log = KotlinLogging.logger {  }

    //@JsonDeserialize(using = CustomAuthorityDeserializer::class)
    @JsonIgnore
    val collection:MutableList<GrantedAuthority> = ArrayList()


    init {
        this.collection.add(GrantedAuthority { "ROLE_" +  member.role})
    }


    @JsonIgnore
    override fun getAuthorities(): MutableList<GrantedAuthority> {
        log.info { "Role 검증" }
        return this.collection
    }

    override fun getPassword(): String {
        return member.password
    }

    override fun getUsername(): String {
        return member.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }



}