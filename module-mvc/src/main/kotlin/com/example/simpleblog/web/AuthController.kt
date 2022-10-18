package com.example.simpleblog.web

import com.example.simpleblog.service.AuthService
import com.example.simpleblog.web.dto.LoginDto
import com.example.simpleblog.web.dto.common.CmResDto
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid


@RequestMapping("/auth")
@RestController
class AuthController (
    private val authService: AuthService
) {

    val log = KotlinLogging.logger {  }

    @GetMapping("/login")
    fun login(session: HttpSession){
        session.setAttribute("principal", "pass")
    }

    @PostMapping("/member")
    fun joinApp(@Valid @RequestBody dto: LoginDto): CmResDto<*> {
        return CmResDto(HttpStatus.OK, "회원가입", authService.saveMember(dto))
    }

}