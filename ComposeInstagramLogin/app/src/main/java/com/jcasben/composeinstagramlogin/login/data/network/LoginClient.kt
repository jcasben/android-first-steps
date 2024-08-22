package com.jcasben.composeinstagramlogin.login.data.network

import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/f6d119db-ab25-4a22-8b9a-98f78dbb6aa2")
    suspend fun doLogin(user: String, password: String): Response {

    }
}