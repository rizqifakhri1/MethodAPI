package com.rizqi.methodapi.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val dataLogin: DataLogin,
    @SerializedName("success")
    val success: Boolean
)