package com.example.armmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Request

@Dao
interface RequestDAO {

    @Query("SELECT * FROM request_table WHERE status IN ('Создан', 'В работе')")
    fun getRequests(): LiveData<List<Request>>

    @Query("SELECT * FROM request_table WHERE status = 'Выполнен'")
    fun getCompletedRequests(): LiveData<List<Request>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(request: Request)

    @Update
    fun update(request: Request)

    @Query("DELETE FROM request_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Request>)

    @Delete
    fun deleteRequest(request: Request)

    @Query("SELECT COUNT(*) FROM request_table")
    fun getRequestCount(): Int

}