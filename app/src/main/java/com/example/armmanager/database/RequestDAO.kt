package com.example.armmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.armmanager.vo.Request

@Dao
interface RequestDAO {

    @Query("SELECT * FROM request_table")
    fun getRequests(): LiveData<List<Request>>

    @Query("SELECT * FROM request_table where status = 1")
    fun getCompletedRequests(): LiveData<List<Request>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(request: Request)

    @Update
    suspend fun update(request: Request)

    @Query("DELETE FROM request_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM request_table")
    suspend fun getRequestCount(): Int

}