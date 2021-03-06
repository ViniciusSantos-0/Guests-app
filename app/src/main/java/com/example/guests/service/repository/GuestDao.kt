package com.example.guests.service.repository

import androidx.room.*
import com.example.guests.service.model.GuestModel

@Dao
interface GuestDao {

    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update (gues: GuestModel): Int

    @Delete
    fun delete(guest:GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id:Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getInvited(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>
}