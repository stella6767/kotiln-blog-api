package com.example.simpleblog.mvc.config.security

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

/**
 * https://stackoverflow.com/questions/60708489/cannot-save-an-object-with-grantedauthority-field-no-creators-like-default-con
 * https://stackoverflow.com/questions/36168010/spring-securitycan-not-construct-instance-of-org-springframework-security-core
 */

class CustomAuthorityDeserializer(

) : JsonDeserializer<MutableList<GrantedAuthority>>() {

    private val log = KotlinLogging.logger {  }

    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): MutableList<GrantedAuthority> {


        try {
            val mapper: ObjectMapper = jp.getCodec() as ObjectMapper
            val jsonNode: JsonNode = mapper.readTree(jp)
            val grantedAuthorities: MutableList<GrantedAuthority> = LinkedList()
            val elements: Iterator<JsonNode> = jsonNode.elements()

            while (elements.hasNext()) {
                val next: JsonNode = elements.next()
                val authority: JsonNode = next.get("authority")
                grantedAuthorities.add(SimpleGrantedAuthority(authority.asText()))
            }
            return grantedAuthorities
        }catch (e: JsonParseException) {

            log.error { "????????" + e.stackTraceToString() }
            throw  e
        }


    }
}