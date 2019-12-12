package br.com.crudkotlin.models

import javax.validation.constraints.NotNull

data class UserCredentialsDTO (@field:NotNull val email: String = "",
                          @field:NotNull val password: String = "")