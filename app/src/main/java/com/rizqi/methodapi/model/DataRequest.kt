package com.rizqi.methodapi.model


import com.google.gson.annotations.SerializedName

data class DataRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("username")
    val username: String
)