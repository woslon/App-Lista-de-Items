package com.example.my_list_itens.di

import android.content.Context
import androidx.room.Room
import com.example.my_list_itens.data.local.database.AppDatabase
import com.example.my_list_itens.data.local.dao.itemDao.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "item_db"
        ).build()
    }

    @Provides
    fun provideItemDao(
        db: AppDatabase
    ): ItemDao {
        return db.itemDao()
    }
}