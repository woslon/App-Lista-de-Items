package com.example.my_list_itens.data.repository

import com.example.my_list_itens.data.local.dao.itemDao.ItemDao
import com.example.my_list_itens.data.local.entity.Item
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
class ItemRepository @Inject constructor(
        private val itemDao: ItemDao
) {
    suspend fun add(item: Item) {
        itemDao.insert(item)
    }
    suspend fun upsert(item: Item){
        itemDao.upsert(item)
    }
    fun getAll(): Flow<List<Item>> = itemDao.getAll() 
    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }
    suspend fun deleteAll()  =  itemDao.deleteAll()

}