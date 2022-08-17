package com.example.foodiesapp.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface userInterface {
    @POST("/api/auth/signup")
    fun register(@Body request:User): Call<ResponseModel>?

    @POST("/api/auth/signin")
    fun login(@Body request:User): Call<responseLogin>?

    @GET("")
    suspend fun getallUsers():   AllUsers
}


