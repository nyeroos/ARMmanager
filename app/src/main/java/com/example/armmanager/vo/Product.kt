package com.example.armmanager.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product_table")
class Product (
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int,

    @ColumnInfo(name = "productName")
    val productName: String)