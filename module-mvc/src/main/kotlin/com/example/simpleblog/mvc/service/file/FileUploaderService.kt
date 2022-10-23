package com.example.simpleblog.mvc.service.file

import org.springframework.web.multipart.MultipartFile

interface FileUploaderService {

    fun upload(file: MultipartFile): String
}