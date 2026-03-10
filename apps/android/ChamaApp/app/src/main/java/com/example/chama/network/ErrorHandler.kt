package com.example.chama.network

import retrofit2.Response

object ErrorHandler {

    fun <T> handleResponse(response: Response<T>): Result<T> {
        return when {
            response.isSuccessful -> {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty response body"))
            }
            response.code() == 401 -> {
                TokenManager.clearToken()
                Result.failure(Exception("Unauthorized. Please login again."))
            }
            response.code() == 404 -> {
                Result.failure(Exception("Resource not found."))
            }
            response.code() == 500 -> {
                Result.failure(Exception("Server error. Please try again later."))
            }
            else -> {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        }
    }
}