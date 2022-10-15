package com.example.simpleblog.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.addSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Configuration
class ObjectMapperConfig {





    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()

        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDateTime::class, CustomLocalDateTimeSerializer() )
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



}