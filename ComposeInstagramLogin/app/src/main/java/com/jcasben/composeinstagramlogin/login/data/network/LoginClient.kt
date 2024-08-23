package com.jcasben.composeinstagramlogin.login.data.network

import com.jcasben.composeinstagramlogin.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/012d41e1-f43f-4ceb-8de7-34eef733ac5c")
    suspend fun doLogin(): Response<LoginResponse>
}