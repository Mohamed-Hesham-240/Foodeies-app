package com.example.foodiesapp.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface userInterface {
    @POST("/auth/register")
     fun register(@Body request:User):Call<ResponseModel>?

    @GET("")
    suspend fun getallUsers():   AllUsers
}


