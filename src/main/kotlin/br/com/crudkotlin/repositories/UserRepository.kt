package br.com.crudkotlin.repositories

import br.com.crudkotlin.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String>{

    fun findByUsernameOrEmail(username: String,email: String): User?

}