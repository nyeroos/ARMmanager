package com.example.armmanager.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "request_table")
class Request(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "request_number")
    val number: Int,

    @ColumnInfo(name = "request_name")
    val name: String,

    @ColumnInfo(name = "customer")
    val customer: String,

    @ColumnInfo(name = "expected_date")
    val expectedDate: String,

    @ColumnInfo(name = "creation_date")
    val creationDate: String,

    @ColumnInfo(name = "actual_date")
    val actualDate: String,

    @ColumnInfo(name = "user")
    val user: Int,

    @ColumnInfo(name = "status")
    val status: String,
)
