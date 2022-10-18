package com.example.simpleblog.config.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.AnonymousAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import io.findify.s3mock.S3Mock
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Configuration
class EmbedeedS3Config {

    private val log = KotlinLogging.logger {  }
    val port = 7777
    private val api = S3Mock.Builder().withPort(port).withInMemoryBackend().build()
    private val serviceEndpoint = "http://localhost:7777"
    private val region = "us-west-2"

    @PostConstruct
    fun init(){
        log.info { "mocking S3 API Start!!" }
        this.api.start();
    }


    @Bean
    fun amazonS3 (): AmazonS3 {

        val endpoint = EndpointConfiguration(serviceEndpoint, region)
        val client: AmazonS3 = AmazonS3ClientBuilder
            .standard()
            .withPathStyleAccessEnabled(true)
            .withEndpointConfiguration(endpoint)
            .withCredentials(AWSStaticCredentialsProvider(AnonymousAWSCredentials()))
            .build()

        log.info { "embeddedAmazonS3===> ${client}" }
        return client
    }



    @PreDestroy
    fun destory(){

        log.info { "s3 Mock API ShutDown" }

        this.api.shutdown()
    }



}