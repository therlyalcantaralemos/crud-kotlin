package br.com.crudkotlin.extensions

import br.com.crudkotlin.models.User

fun User.toUpdateWith(otherUser: User): User{
    this.username = otherUser.username;
    this.email = otherUser.email;
    return this;
}

