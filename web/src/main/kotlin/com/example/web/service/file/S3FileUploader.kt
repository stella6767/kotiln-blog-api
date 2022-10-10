package com.example.web.service.file


import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.web.config.aws.LocalS3Config
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.net.URL
import java.util.*
import javax.annotation.PostConstruct


//@Component
class S3FileUploader(
    private val amazonS3: AmazonS3
) : FileUploader {

    private val log = KotlinLogging.logger {  }

    //val bucketName: String = amazonS3.listBuckets().map { it.name }.first()

    @PostConstruct
    fun init(){
        log.info { "s3FileUploader ====>   $amazonS3 주입받음" }
    }


    override fun upload(
        file: MultipartFile,
    ): String {

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
        return amazonS3.getUrl(LocalS3Config.AwsBucket.KANGBLOG.code, fileName).toString()
    }



}