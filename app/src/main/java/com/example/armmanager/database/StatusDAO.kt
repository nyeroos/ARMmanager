package com.example.armmanager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.armmanager.vo.Request


@Dao
interface StatusDAO {

//    @Query("SELECT * FROM status_table")
//    fun getStatus(): LiveData<List<Request>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(sta: Request)
//
//    @Query("DELETE FROM request_table")
//    suspend fun deleteAll()
//
//    @Query("SELECT COUNT(*) FROM request_table")
//    suspend fun getRequestCount(): Int
}