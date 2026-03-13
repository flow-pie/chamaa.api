package com.example.chama.data

import com.example.chama.models.ContributionRequest
import com.example.chama.models.ContributionResponse
import com.example.chama.network.ApiClient
import com.example.chama.network.TokenManager

class ContributionRepository {

    private val apiService = ApiClient.contributionApiService

    suspend fun makeContribution(request: ContributionRequest): Result<ContributionResponse> {
        return try {
            val token = TokenManager.getToken() ?: ""
            val response = apiService.makeContribution(
                token = "Bearer $token",
                request = request
            )
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                if (body.success) {
                    Result.success(body)
                } else {
                    Result.failure(Exception(body.message))
                }
            } else {
                Result.failure(Exception("Server error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Network error. Please try again."))
        }
    }
}