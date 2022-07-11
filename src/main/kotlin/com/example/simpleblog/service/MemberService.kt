package com.example.simpleblog.service

import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
        private val memberRepository: MemberRepository
) {


    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<Member> {
        return memberRepository.findAllByPage(pageable)
    }


}