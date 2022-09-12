//package com.example.simpleblog.config.security
//
//import com.fasterxml.jackson.core.JsonParser
//import com.fasterxml.jackson.core.JsonProcessingException
//import com.fasterxml.jackson.databind.DeserializationContext
//import com.fasterxml.jackson.databind.JsonNode
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer
//import org.springframework.security.core.userdetails.UserDetails
//import java.io.IOException
//
//
//
//
//class PrincipalInterfaceDeserialize(
//    vc: Class<*>
//): StdDeserializer<UserDetails?>(vc)  {
//
//    override fun deserialize(p0: JsonParser, p1: DeserializationContext): UserDetails {
//        val node: JsonNode = p0.readValueAsTree()
//        val member: String = node.get("member").asText()
//        val impl = PrincipalDetails()
//        impl.member = member
//
//        return impl
//    }
//}
//
//
