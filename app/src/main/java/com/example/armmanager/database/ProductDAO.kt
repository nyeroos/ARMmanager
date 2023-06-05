package com.example.armmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.armmanager.vo.Product

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product_table")
    fun getProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Product>)

    @Update
    fun update(product: Product)

    @Query("DELETE FROM product_table")
    fun deleteAll()

    @Delete
     fun deleteProduct(product: Product)

    @Query("SELECT COUNT(*) FROM product_table")
     fun getProductsCount(): Int

}