package com.example.web.service.file

import org.springframework.web.multipart.MultipartFile
import java.net.URL

interface FileUploader {

    fun upload(file: MultipartFile):String


}