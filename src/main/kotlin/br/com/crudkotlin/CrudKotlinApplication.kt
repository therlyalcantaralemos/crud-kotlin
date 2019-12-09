package br.com.crudkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories
@EnableMongoAuditing
@SpringBootApplication
class CrudKotlinApplication

fun main(args: Array<String>) {
	runApplication<CrudKotlinApplication>(*args)
}
