package com.example.armmanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.User

// Annotates class to be a Room Database with a table (entity) of the Request class
@Database(
    entities = [Request::class,
                User::class,
                Product::class],
    version = 3,
    exportSchema = false)
abstract class ArmRoomDatabase : RoomDatabase() {

    abstract fun requestDao(): RequestDAO

    abstract fun productDao(): ProductDAO
}