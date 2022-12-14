package com.example.usersrandom.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfazUsers {
    @GET("api/")
    fun getUser(): Call<Users>
}