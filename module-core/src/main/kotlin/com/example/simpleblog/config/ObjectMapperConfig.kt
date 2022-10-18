package com.example.simpleblog.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.addDeserializer
import com.fasterxml.jackson.module.kotlin.addSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Configuration
class ObjectMapperConfig {

    /**
     * serialize(직렬화) : java 객체를 json 객체로 변환하는 작업!!
     * deserialize(역직렬화) : json 객체를 java 객체로 변환하는 작업!!
     */


    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        val javaTimeModule = JavaTimeModule()

        javaTimeModule.addSerializer(LocalDateTime::class, CustomLocalDateTimeSerializer() )
        javaTimeModule.addDeserializer(LocalDateTime::class, CustomLocalDateTimeDeSerializer())
        mapper.registerModule(javaTimeModule)

        mapper.registerModule(
            KotlinModule.Builder()
                .configure(KotlinFeature.StrictNullChecks, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .build()
        )

        return mapper
    }


    class CustomLocalDateTimeSerializer(): JsonSerializer<LocalDateTime>() {

        private val dateTimeFormat = "yyyy-MM-dd HH:mm:ss"
        private val formatter = DateTimeFormatter.ofPattern(dateTimeFormat, Locale.KOREA)

        override fun serialize(value: LocalDateTime, gen: JsonGenerator, serializers: SerializerProvider) {
            gen.writeString(formatter.format(value))
        }
    }


    class CustomLocalDateTimeDeSerializer(): JsonDeserializer<LocalDateTime>(){

        private val dateTimeFormat = "yyyy-MM-dd HH:mm:ss"
        private val formatter = DateTimeFormatter.ofPattern(dateTimeFormat, Locale.KOREA)

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDateTime {
            return LocalDateTime.parse(p.text, formatter)
        }

    }


}