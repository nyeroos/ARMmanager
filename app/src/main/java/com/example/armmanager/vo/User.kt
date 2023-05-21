package com.example.armmanager.vo

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class User(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("user_name")
    val name: String?,
    @field:SerializedName("login")
    val login: String?,
    @field:SerializedName("password")
    val password: String?,

)