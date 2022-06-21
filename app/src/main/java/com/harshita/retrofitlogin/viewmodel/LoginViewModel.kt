package com.harshita.retrofitlogin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.harshita.retrofitlogin.data.api.ApiClient
import com.harshita.retrofitlogin.data.api.methods.UserApi
import com.harshita.retrofitlogin.data.api.request.LoginRequest
import com.harshita.retrofitlogin.data.api.response.BaseResponse
import com.harshita.retrofitlogin.data.api.response.LoginResponse
import com.harshita.retrofitlogin.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}