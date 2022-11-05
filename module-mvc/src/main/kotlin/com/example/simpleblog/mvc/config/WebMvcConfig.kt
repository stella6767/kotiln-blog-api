package com.example.simpleblog.mvc.config

import com.example.simpleblog.core.domain.post.Post
import com.example.simpleblog.core.util.dto.SearchCondition.SearchType
import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import org.springframework.web.method.HandlerTypePredicate
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(

) : WebMvcConfigurer {

    private val log = KotlinLogging.logger {  }
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(StringToEnumConverter())
    }





    override fun configurePathMatch(configurer: PathMatchConfigurer) {

        val apiVersion = "/v1"
        val basePackage = "com.example.simpleblog.mvc.web"

        configurer.addPathPrefix(apiVersion, HandlerTypePredicate.forBasePackage(basePackage))
    }


    class StringToEnumConverter : Converter<String, SearchType> {

        override fun convert(source: String): SearchType? {
            println("source==>$source")

            return SearchType.valueOf(source.uppercase())
        }
    }

}


