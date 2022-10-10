package com.example.web.service.file

import com.example.web.util.MvcUtil
import mu.KotlinLogging
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.ServletContext


@Component
class LocalFileUploader(
    private val resourceLoader: ResourceLoader
) : FileUploader {


    private val log = KotlinLogging.logger {  }

    @PostConstruct
    fun init() {
        try {
            log.info { "폴더 생성" }
            Files.createDirectories(Paths.get(localImgFolder))
        } catch (e: IOException) {
            throw RuntimeException("Could not create upload folder!")
        }
    }


    override fun upload(file: MultipartFile): String {
        val uuid = UUID.randomUUID() //같은 이름의 사진이 들어오면 충돌나므로 방지하기 위해
        val imageFileName = uuid.toString() + "_" + file.originalFilename
        val imageFilePath: Path = Paths.get(localImgFolder + imageFileName)
        Files.write(imageFilePath, file.bytes)
        return  MvcUtil.getBaseUrl() + "/static/postImg/$imageFileName"
    }

    fun getResource(fileName:String){

        val file: File = ResourceUtils.getFile(
            "classpath:postImg/${fileName}"
        )



    }



    companion object {
        const val localImgFolder = "web/src/main/resources/static/postImg/"
    }

}