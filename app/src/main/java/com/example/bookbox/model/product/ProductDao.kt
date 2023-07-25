package com.example.bookbox.model.product

import androidx.room.*
import com.example.bookbox.model.product.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM product WHERE type = :type")
    fun getAll(type: Int):List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //ekleme
    fun insert(productEntity: ProductEntity)

    @Delete //silme
    fun delete(productEntity: ProductEntity)

    @Query("DELETE FROM product WHERE id = :id AND type = :type") //sorgu
    fun deleteById(id: Int, type:Int)

    @Update
    fun update(productEntity: ProductEntity)

    @Query("SELECT * From product Where id = :id AND type = :type")
    fun getById(id: Int, type: Int): List<ProductEntity>
}