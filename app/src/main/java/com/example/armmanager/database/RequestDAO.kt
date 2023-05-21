package com.example.armmanager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.armmanager.vo.Request

@Dao
interface RequestDAO {

    @Query("SELECT * FROM request_table")
    fun getRequests(): LiveData<List<Request>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(request: Request)

    @Query("DELETE FROM request_table")
    suspend fun deleteAll()

}