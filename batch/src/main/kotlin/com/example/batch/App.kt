package com.example.batch

import com.example.common.domain.member.Member
import com.example.common.domain.member.MemberRepository
import org.springframework.stereotype.Component


@Component
class App(
    private val memberRepository: MemberRepository
) {



}


fun main() {

    val member = Member(email = "", password = "")

    println(member)


}