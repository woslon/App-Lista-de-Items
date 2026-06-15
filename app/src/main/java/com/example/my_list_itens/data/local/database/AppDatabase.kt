package com.example.my_list_itens.data.local.database

import com.example.my_list_itens.data.local.entity.Item
import com.example.my_list_itens.data.local.dao.itemDao.ItemDao
import androidx.room.RoomDatabase
import androidx.room.Database


@Database(entities = [Item::class], version = 1)

abstract  class AppDatabase : RoomDatabase(){
    abstract fun itemDao() : ItemDao

}
