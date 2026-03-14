package com.example.chama.models

import com.google.gson.annotations.SerializedName

data class ContributionResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("transaction_reference")
    val transactionReference: String?,

    @SerializedName("group_name")
    val groupName: String?,

    @SerializedName("amount")
    val amount: Double?
)