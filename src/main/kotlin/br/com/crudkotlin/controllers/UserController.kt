package br.com.crudkotlin.controllers

import br.com.crudkotlin.models.User
import br.com.crudkotlin.models.UserDTO
import br.com.crudkotlin.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody userDTO: UserDTO): User{
        return userService.create(userDTO);
    }

    @PostMapping("/singin")
    fun teste(){
        //return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: String, @Valid @RequestBody userDTO: UserDTO): User {
        return userService.update(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String){
        userService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: String) : User{
        return userService.getById(id);
    }

}