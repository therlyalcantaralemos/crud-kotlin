package br.com.crudkotlin.models

import javax.validation.constraints.NotNull

data class UserDTO(@field:NotNull val username: String = "",
                   @field:NotNull val email: String = "")