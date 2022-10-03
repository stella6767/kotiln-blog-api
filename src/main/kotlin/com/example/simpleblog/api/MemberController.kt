package com.example.simpleblog.api

import com.example.simpleblog.domain.member.LoginDto
import com.example.simpleblog.service.MemberService
import com.example.simpleblog.util.value.CmResDto
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid



@RestController
class MemberController(
        private val memberService: MemberService
) {

    @GetMapping("/members")
    fun findAll(@PageableDefault(size = 10) pageable: Pageable, session: HttpSession): CmResDto<*> {
        return CmResDto(HttpStatus.OK, "find All Members", memberService.findAll(pageable))
    }


    @GetMapping("/member/{id}")
    fun findById(@PathVariable id:Long): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "find Member by id", memberService.findMemberById(id))
    }


    @DeleteMapping("/member/{id}")
    fun deleteById(@PathVariable id:Long): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "delete Member by id", memberService.deleteMember(id))
    }


}