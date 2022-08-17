package com.example.foodiesapp.login

import retrofit2.Call
import retrofit2.http.*

interface userInterface {

    @POST("auth/register")
    fun register(@Body request:User): Call<ResponseModel>?

   // @Headers({ "Content-Type: application/json;charset=UTF-8"})

    @FormUrlEncoded
    @POST("auth/login")
    fun login(@Field("email")mail:String ,@Field("password")pass:String): Call<responseLogin>?

    @GET("")
    suspend fun getallUsers():   AllUsers
}


