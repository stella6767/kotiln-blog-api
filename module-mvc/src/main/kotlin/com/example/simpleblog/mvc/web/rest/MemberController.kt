package com.example.simpleblog.mvc.web.rest

import com.example.simpleblog.mvc.service.member.MemberQueryService
import com.example.simpleblog.mvc.service.member.MemberService
import com.example.simpleblog.mvc.web.dto.common.CmResDto
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession


@RestController
class MemberController(
        private val memberService: MemberService,
        private val memberQueryService: MemberQueryService,
) {

    @GetMapping("/members")
    fun findAll(@PageableDefault(size = 10) pageable: Pageable, session: HttpSession): CmResDto<*> {
        return CmResDto(HttpStatus.OK, "find All Members", memberQueryService.findAll(pageable))
    }


    @GetMapping("/member/{id}")
    fun findById(@PathVariable id:Long): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "find Member by id", memberQueryService.findMemberById(id))
    }


    @DeleteMapping("/member/{id}")
    fun deleteById(@PathVariable id:Long): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "delete Member by id", memberService.deleteMember(id))
    }


}