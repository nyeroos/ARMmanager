package com.example.armmanager.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product_table")
class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "product_name")
    val name: String)