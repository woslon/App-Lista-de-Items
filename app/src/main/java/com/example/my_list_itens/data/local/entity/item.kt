package com.example.my_list_itens.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val customer: String,
    val itemName: String,
    val quantity: Int,
    val price: Double,
    val date: String
)
