package com.example.simpleblog.config

import com.example.simpleblog.util.dto.SearchType
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.type.TypeFactory
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*

@Configuration
class WebMvcConfig(

) : WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(StringToEnumConverter())
    }
}


class StringToEnumConverter : Converter<String, SearchType> {

    override fun convert(source: String): SearchType? {
        println("source==>$source")

        return SearchType.valueOf(source.uppercase())
    }

}
