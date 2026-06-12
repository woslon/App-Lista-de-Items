package com.example.my_list_itens.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_list_itens.data.local.entity.Item
import com.example.my_list_itens.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    fun add(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.add(item)
    }

    fun upsert(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.upsert(item)
    }

    fun getAll() = repository.getAll()

    fun delete(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}