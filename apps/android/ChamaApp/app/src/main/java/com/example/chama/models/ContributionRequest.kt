package com.example.chama.models


import com.google.gson.annotations.SerializedName

data class ContributionRequest(
    @SerializedName("group_id")
    val groupId: String,

    @SerializedName("amount")
    val amount: Double,

    @SerializedName("payment_method")
    val paymentMethod: String,       // "mpesa" | "airtel" | "paypal"

    @SerializedName("phone_number")
    val phoneNumber: String? = null, // M-Pesa / Airtel

    @SerializedName("email")
    val email: String? = null        // PayPal
)