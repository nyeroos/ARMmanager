package com.example.armmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.armmanager.vo.Request

@Dao
interface RequestDAO {

    @Query("SELECT * FROM request_table WHERE status IN ('Создан', 'В работе')")
    fun getRequests(): LiveData<List<Request>>

    @Query("SELECT * FROM request_table WHERE status = 'Выполнен'")
    fun getCompletedRequests(): LiveData<List<Request>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(request: Request)

    @Update
    suspend fun update(request: Request)

    @Query("DELETE FROM request_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteRequest(request: Request)

    @Query("SELECT COUNT(*) FROM request_table")
    suspend fun getRequestCount(): Int

}