package com.example.armmanager.api

import com.example.armmanager.vo.Request
import com.google.gson.annotations.SerializedName

data class RequestProductResponse(
    @SerializedName("amount")
    val amount: Int = 0,
    @SerializedName("items")
    val items: List<Request>
) {
    var nextPage: Int? = null
}