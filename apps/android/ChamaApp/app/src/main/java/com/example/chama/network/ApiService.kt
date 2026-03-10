package com.example.chama.network

import com.example.chama.models.BaseResponse
import com.example.chama.models.GroupModel
import com.example.chama.models.LoanModel
import com.example.chama.models.UserModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // User endpoints
    @POST("users")
    suspend fun createUser(@Body user: UserModel): Response<BaseResponse<UserModel>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Long): Response<BaseResponse<UserModel>>

    // Group endpoints
    @POST("groups")
    suspend fun createGroup(@Body group: GroupModel): Response<BaseResponse<GroupModel>>

    @GET("groups")
    suspend fun getGroups(): Response<BaseResponse<List<GroupModel>>>

    // Loan endpoints
    @POST("loans")
    suspend fun requestLoan(@Body loan: LoanModel): Response<BaseResponse<LoanModel>>

    @GET("loans/{id}")
    suspend fun getLoan(@Path("id") id: Long): Response<BaseResponse<LoanModel>>
}