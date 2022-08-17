package com.example.foodiesapp.login

import com.example.foodiesapp.Products
import retrofit2.Call
import retrofit2.http.*

interface userInterface {

    @POST("auth/register")
    fun register(@Body request:User): Call<ResponseModel>?


    @FormUrlEncoded
    @POST("auth/login")
    fun login(@Field("email")mail:String ,@Field("password")pass:String): Call<responseLogin>?

    @GET("browse/getAllItems")
    suspend fun getallProducts(@Header("Authorization")access_token :String): Products
}


