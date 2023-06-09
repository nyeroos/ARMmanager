package com.example.armmanager.ui.auth

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.armmanager.api.ArmService
import com.example.armmanager.vo.dto.LoginDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class AuthorizationViewModel @Inject constructor(
    private val application: Application,
    private val sharedPreferences: SharedPreferences?,
    private val armService: ArmService
) : ViewModel() {

    val token = MutableLiveData<String?>()


    fun login(email: String, password: String) {
        val request = LoginDto(email, password)
        armService.login(request).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    val authToken = response.headers()["Authorization"]
                    if (!authToken.isNullOrEmpty()) {
                        sharedPreferences?.edit()?.putString("token", authToken)?.apply()
                    }

                } else {
                    Toast.makeText(
                        application, // получаем контекст SharedPreferences
                        "Пошел нахуй", // сообщение об ошибке с описанием
                        Toast.LENGTH_SHORT // продолжительность отображения Toast
                    ).show()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }
        })
    }


}