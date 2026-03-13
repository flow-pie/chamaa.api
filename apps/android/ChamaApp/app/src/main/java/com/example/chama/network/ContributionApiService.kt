package com.example.chama.network

import com.example.chama.models.ContributionRequest
import com.example.chama.models.ContributionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ContributionApiService {

    @POST("api/contributions")
    suspend fun makeContribution(
        @Header("Authorization") token: String,
        @Body request: ContributionRequest
    ): Response<ContributionResponse>
}