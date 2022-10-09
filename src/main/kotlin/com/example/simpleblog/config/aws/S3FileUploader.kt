package com.example.simpleblog.config.aws


import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.net.URL
import java.util.*
import javax.annotation.PostConstruct


@Component
class S3FileUploader(
    private val amazonS3: AmazonS3
) {

    private val log = KotlinLogging.logger {  }

    //val bucketName: String = amazonS3.listBuckets().map { it.name }.first()

    @PostConstruct
    fun init(){
        log.info { "s3FileUploader ====>   $amazonS3 주입받음" }
    }


    fun upload(
        file:MultipartFile,
    ): URL? {

        val uuid = UUID.randomUUID().toString()
        val fileName = "postImg" + "/" + uuid + file.originalFilename

        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = file.contentType
        objectMetadata.contentLength = file.size

        val objectRequest = PutObjectRequest(
            LocalS3Config.AwsBucket.KANGBLOG.code,
            fileName,
            file.inputStream,
            objectMetadata
        )

        this.amazonS3.putObject(objectRequest.withCannedAcl(CannedAccessControlList.BucketOwnerFullControl))//
        val url = this.amazonS3.getUrl(LocalS3Config.AwsBucket.KANGBLOG.code, "postImg/${file.originalFilename}")

        return url
    }

//    fun upload(multipartFile: MultipartFile, dirName:String){
//
//        val uuid = UUID.randomUUID().toString()
//        val convertFile = File(uuid + "--" +multipartFile.originalFilename)
//
//
//        val uploadFile: File = when {
//            convertFile.createNewFile() -> {
//                FileOutputStream(convertFile).use { fos: FileOutputStream ->
//                    fos.write(multipartFile.bytes)
//                }
//                Optional.of(convertFile)
//            }
//            else -> {
//                log.warn { "file 생성실패" }
//                convertFile.delete()
//                Optional.empty<File>()
//            }
//        }.orElseThrow()
//
//        uploadFile?.let { file: File ->
//
//            val fileName = "$dirName/${file.name}"
//
//
//
//            //amazonS3.putObject()
//
//
//        }
//
//    }




}