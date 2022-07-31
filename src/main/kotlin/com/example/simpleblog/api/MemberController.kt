package com.example.simpleblog.api

import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.MemberRes
import com.example.simpleblog.service.MemberService
import com.example.simpleblog.util.value.CmResDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MemberController(
        private val memberService: MemberService
) {


    @GetMapping("/members")
    fun findAll(): CmResDto<*> {
        return CmResDto(HttpStatus.OK, "find All Members", memberService.findAll())
    }






}