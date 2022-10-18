package com.example.simpleblog.config.jpa.converts

import com.example.simpleblog.core.domain.post.PostType
import javax.persistence.AttributeConverter
import javax.persistence.Convert


@Convert
class PostTypeConverter : AttributeConverter<PostType, String>{

    override fun convertToDatabaseColumn(attribute: PostType?): String? {

        return attribute?.name
    }

    override fun convertToEntityAttribute(dbData: String?): PostType {

        return PostType.ofCode(dbData)
    }

}