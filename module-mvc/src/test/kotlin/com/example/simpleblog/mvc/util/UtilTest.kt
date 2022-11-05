package com.example.simpleblog.mvc.util


import com.example.simpleblog.mvc.config.redis.repo.HashMapServiceImpl
import com.example.simpleblog.mvc.config.redis.repo.InMemoryService
import com.example.simpleblog.mvc.service.file.FileUploaderService
import com.example.simpleblog.mvc.service.file.LocalFileUploaderServiceImpl



import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


class UtilTest {

    private val log = KotlinLogging.logger {  }


    @Test
    fun fastExcelTest(){

    }


    @Test
    fun localFileUploaderTest(){

        val fileUploader: FileUploaderService = LocalFileUploaderServiceImpl()
        val path= Paths.get("src/test/resources/test/favicon.jpg")

        val name = "favicon.jpg"
        val originalFileName = "favicon.jpg"
        val contentType = MediaType.IMAGE_JPEG_VALUE
        val content = Files.readAllBytes(path)

        val mockFile: MultipartFile = MockMultipartFile(
            name,
            originalFileName, contentType, content
        )

        fileUploader.upload(mockFile)

    }



    @Test
    fun hashMapRepoTest(){

        val repo: InMemoryService = HashMapServiceImpl()
        val numberOfThreads = 1000
        val service = Executors.newFixedThreadPool(10)
        val latch = CountDownLatch(numberOfThreads)

        for (index in 1..numberOfThreads){
            service.submit{
                repo.save(index.toString(), index)
                latch.countDown()
            }
        }

        latch.await()

        Thread.sleep(1000)

        val results = repo.findAll()
        Assertions.assertThat(results.size).isEqualTo(numberOfThreads)

    }


    @Test
    fun errorLogTest(){

        //throw RuntimeException("error")
        log.error { "error!!" }
    }

    @Test
    fun bcryptEncodeTest(){

        val encoder = BCryptPasswordEncoder()

        val encpassword = encoder.encode("1234")

        val matches:Boolean = encoder.matches("1234", "\$2a\$10\$5pHCXu/JF3075RUiZ6Ina.ZG3TeuOPH6NB0/LrMWL7XLvu2rNJ3Ty")

        log.info { matches }
        log.info { encpassword }

    }


    @Test
    fun generateJwtTest() {


    }






}