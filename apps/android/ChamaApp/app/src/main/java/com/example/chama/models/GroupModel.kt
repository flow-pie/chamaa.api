package com.example.chama.models

data class GroupModel(
    val id: Long? = null,
    val name: String,
    val description: String,
    val creatorId: Long,
    val targetAmount: Double
)