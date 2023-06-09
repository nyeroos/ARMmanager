package com.example.armmanager.vo.dto

data class AddRequestDto (val requestNumber: Int, val requestName: String, val customer: String, val expectedDate: String,
                          val creationDate: String, val actualDate: String, val status: String)