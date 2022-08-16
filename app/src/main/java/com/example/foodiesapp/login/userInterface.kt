package com.example.foodiesapp.login

import retrofit2.http.GET
import retrofit2.http.POST

interface userInterface {
    @POST

    @GET("")
    suspend fun getallUsers():   AllUsers
}


