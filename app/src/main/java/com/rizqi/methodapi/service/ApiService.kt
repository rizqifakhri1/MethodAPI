package com.rizqi.methodapi.service

import com.rizqi.methodapi.model.LoginRequest
import com.rizqi.methodapi.model.LoginResponse
import com.rizqi.methodapi.model.RegisterRequest
import com.rizqi.methodapi.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/auth/login")
    fun loginUser(@Body request : LoginRequest): Call<LoginResponse>

    @POST("api/v1/auth/register")
    fun registerUser(@Body request : RegisterRequest): Call<RegisterResponse>


}