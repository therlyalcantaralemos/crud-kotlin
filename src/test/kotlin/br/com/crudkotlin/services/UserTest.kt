package br.com.crudkotlin.services

import br.com.crudkotlin.models.User
import br.com.crudkotlin.models.UserDTO
import br.com.crudkotlin.repositories.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RunWith(JUnit4::class)
class UserTest {

    @MockK
    lateinit var userRepository: UserRepository

    @MockK
    lateinit var objectMapper: ObjectMapper;

    @InjectMockKs
    lateinit var userService: UserService

    private var user: User = User()
    private var userDTO: UserDTO = UserDTO()

    @Before
    fun setUp() {
        MockKAnnotations.init(this);
        userDTO = UserDTO("therly","therly@gmail.com")
        user = User("132456789","therly","therly@gmail.com")
    }

    @Test
    fun shouldCreate() {
        every { userRepository.findByUsernameOrEmail(userDTO.username, userDTO.email) } returns null
        every { objectMapper.convertValue(userDTO, User::class.java) } returns user
        every { userRepository.save(user) } returns user

        val userSave = userService.create(userDTO)

        assert(userSave.id == user.id)
    }

    @Test(expected = ResponseStatusException::class)
    fun shouldNotCreate() {
        every { userRepository.findByUsernameOrEmail(userDTO.username, userDTO.email) } returns user
        userService.create(userDTO)
    }

}