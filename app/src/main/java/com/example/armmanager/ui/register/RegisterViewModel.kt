package com.example.armmanager.ui.register

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.armmanager.api.ArmService
import com.example.armmanager.vo.dto.LoginDto
import com.example.armmanager.vo.dto.RegisterDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val application: Application,
    private val armService: ArmService
) : ViewModel() {
    fun register(username: String, password: String, email: String) {
        val request = RegisterDto(username, email, password)
        armService.register(request).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        application,
                        "Пользователь зарегистрирован",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
