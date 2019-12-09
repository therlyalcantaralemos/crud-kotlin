package br.com.crudkotlin.services


import br.com.crudkotlin.extensions.toUpdateWith
import br.com.crudkotlin.models.User
import br.com.crudkotlin.models.UserDTO
import br.com.crudkotlin.repositories.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService @Autowired constructor (private val userRepository: UserRepository,
                                          private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())){

    fun save(userDTO: UserDTO) : User {
        userRepository.findByUsernameOrEmail(userDTO.username, userDTO.email)?.isPresent ?: throw ResponseStatusException(HttpStatus.FOUND)
        return userRepository.save(objectMapper.convertValue(userDTO, User::class.java))
    }

    fun update(id: String, userDTO: UserDTO) : User{
        val user = getById(id)
        user.toUpdateWith(objectMapper.convertValue(userDTO, User::class.java))
        return userRepository.save(user)
    }

    fun delete(id: String) = userRepository.delete(getById(id))

    fun getById(id: String) : User =  userRepository.findById(id).orElse(null) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

}

