package com.example.chama.models

data class LoanModel(
    val id: Long? = null,
    val borrowerId: Long,
    val groupId: Long,
    val amount: Double,
    val durationInMonths: Int,
    val purpose: String
)