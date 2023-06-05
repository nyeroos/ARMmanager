package com.example.armmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Request class
@Database(
    entities = [Request::class,
                User::class,
                Product::class],
    version = 2,
    exportSchema = false)
public abstract class ArmRoomDatabase : RoomDatabase() {

    abstract fun requestDao(): RequestDAO

    abstract fun productDao(): ProductDAO
}