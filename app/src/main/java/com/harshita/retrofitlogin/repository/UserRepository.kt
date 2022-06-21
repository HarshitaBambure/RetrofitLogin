package com.harshita.retrofitlogin.repository

import com.harshita.retrofitlogin.data.api.methods.UserApi
import com.harshita.retrofitlogin.data.api.request.LoginRequest
import com.harshita.retrofitlogin.data.api.response.LoginResponse
import retrofit2.Response

class UserRepository {

   suspend fun loginUser(loginRequest:LoginRequest): Response<LoginResponse>? {
      return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}