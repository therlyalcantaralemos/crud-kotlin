package br.com.crudkotlin.repositories

import br.com.crudkotlin.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserRepository : MongoRepository<User, String>{

    fun findByUsernameOrEmail(username: String,email: String): Optional<User>?

}