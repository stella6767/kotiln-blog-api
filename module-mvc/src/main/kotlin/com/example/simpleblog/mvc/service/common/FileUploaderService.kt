package com.example.simpleblog.mvc.service.common

import org.springframework.web.multipart.MultipartFile

interface FileUploaderService {

    fun upload(file: MultipartFile): String
}