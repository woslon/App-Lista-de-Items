package com.example.my_list_itens.data.local.dao.itemDao

import com.example.my_list_itens.data.local.entity.Item
import kotlinx.coroutines.flow.Flow
import kotlin.collections.List
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item)

    @Upsert
    suspend fun upsert(item: Item)

    @Query(value = "SELECT * FROM items")
    fun getAll(): Flow<List<Item>>

    @Delete
    suspend fun delete(item : Item)

    @Query("DELETE FROM items")
     suspend fun deleteAll()
}