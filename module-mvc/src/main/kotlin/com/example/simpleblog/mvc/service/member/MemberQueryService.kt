package com.example.simpleblog.mvc.service.member

import com.example.simpleblog.core.domain.member.MemberRepository
import com.example.simpleblog.core.domain.member.MemberRes
import com.example.simpleblog.mvc.exception.MemberNotFoundException

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
        private val memberRepository: MemberRepository
) {


    fun findAll(pageable: Pageable): Page<MemberRes> =
        memberRepository.findMembers(pageable).map {
            it.toDto()
        }



    fun findMemberById(id:Long): MemberRes {
        return memberRepository.findById(id)
            .orElseThrow{
                throw MemberNotFoundException(id.toString())
            }.toDto()
    }


}