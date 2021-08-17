package com.example.guests.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guests.service.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase(){

    abstract fun guestDao(): GuestDao

    companion object{
        private lateinit var INSTANCE: GuestDataBase
        fun getDatabase(context: Context): GuestDataBase{
            if(!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestDB")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}