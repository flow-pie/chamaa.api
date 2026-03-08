package com.example.chama.models

data class UserModel(
    val id: Long? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)