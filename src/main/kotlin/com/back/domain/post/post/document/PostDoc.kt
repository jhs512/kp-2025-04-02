package com.back.domain.post.post.document

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.Setting
import java.time.LocalDateTime

@Document(indexName = "app1_posts")
@Setting(settingPath = "/elasticsearch/posts-settings.json")
data class PostDoc(
    @Id
    @Field(type = FieldType.Keyword)
    val id: String,

    @Field(type = FieldType.Date, format = [], pattern = ["yyyy-MM-dd'T'HH:mm:ss.SSSSSS"])
    val createDate: LocalDateTime,

    @Field(type = FieldType.Date, format = [], pattern = ["yyyy-MM-dd'T'HH:mm:ss.SSSSSS"])
    val modifyDate: LocalDateTime,

    @Field(type = FieldType.Text, analyzer = "korean")
    var title: String,

    @Field(type = FieldType.Text, analyzer = "korean")
    var content: String,
)