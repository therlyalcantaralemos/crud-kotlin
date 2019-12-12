package br.com.crudkotlin.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class User(
    @Id val id: String? = null,
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var roles: List<Role> = listOf(),
    @JsonIgnore @Version var version: Long? = null,
    @JsonIgnore @CreatedDate var createdAt: LocalDateTime? = null,
    @JsonIgnore @LastModifiedDate var updatedAt: LocalDateTime? = null
)


