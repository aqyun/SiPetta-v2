package com.dicoding.sipetta.view.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sipetta.data.UserRepository
import com.dicoding.sipetta.data.api.ErrorResponse
import com.dicoding.sipetta.data.api.LoginResponse
import com.dicoding.sipetta.data.pref.UserModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    val loginResult = MutableLiveData<LoginResponse>()
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val loginResponse = repository.login(email, password)
                val token = loginResponse.accessToken
                if (token != null) {
                    Log.d("Token", "Token: $token")

                    val user = UserModel(email, token, isLogin = true)
                    saveSession(user)
                }

                loginResult.value = loginResponse
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message ?: "Login gagal. Terjadi kesalahan pada aplikasi."
                val errorResponse = LoginResponse(error = true, message = errorMessage)
                loginResult.value = errorResponse
                Log.e("LoginError", "Login failed", e)
            }
        }
    }
}