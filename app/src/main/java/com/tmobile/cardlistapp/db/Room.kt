package com.tmobile.cardlistapp.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardTypeDao {
    @Query("select * from databasecard")
    fun getCards(): LiveData<List<DatabaseCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<DatabaseCard>)
}

@Database(entities = [DatabaseCard::class], version = 1)
abstract class CardsDatabase: RoomDatabase() {
    abstract val cardDao: CardTypeDao
}

private lateinit var INSTANCE: CardsDatabase

fun getDatabase(context: Context): CardsDatabase {
    synchronized(CardsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                CardsDatabase::class.java,
                "cards").build()
        }
    }
    return INSTANCE
}