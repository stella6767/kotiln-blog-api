package com.example.web.config.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.example.web.config.YamlPropertySourceFactory
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource


@Profile(*["local"]) // local 프로파일에서만 생성되는 빈.
@PropertySource(value = ["classpath:aws.yml"], factory = YamlPropertySourceFactory::class)
@Configuration
class LocalS3Config {

    private val log = KotlinLogging.logger {  }

    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String

//    @Value("\${cloud.aws.s3.bucket}")
//    private lateinit var bucket: String

    @Value("\${cloud.aws.credentials.access-key}")
    private val accessKey: String? = null
    @Value("\${cloud.aws.credentials.secret-key}")
    private val secretKey: String? = null


    @Bean //일단 직접 구현체로 등록
    fun amazonS3(): AmazonS3 {

        println("accessKey=>$accessKey")
        val awsCreds = BasicAWSCredentials(accessKey, secretKey)
        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .build()
    }

    enum class AwsBucket(
        val code: String
    ) {
        KANGBLOG("kangblog")
    }

    companion object {
        init {
            System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
        }
    }

}