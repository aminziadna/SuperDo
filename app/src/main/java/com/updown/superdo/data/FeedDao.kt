package com.updown.superdo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FeedDao {
    @Query("select * from ${AppDatabase.FEED_TABLE}")
    suspend fun loadData() : List<ProductInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveProduct(product :ProductInfo)

    @Query("delete from ${AppDatabase.FEED_TABLE}")
    suspend fun deleteAll()

}