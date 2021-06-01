package com.example.basiccalculator.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.basiccalculator.Calculations

@Dao
interface CalcDao {

    @Query("SELECT * FROM Calculations ORDER BY id DESC")
    fun getAll(): LiveData<List<Calculations>>

    @Insert()
    suspend fun save(historyItem: Calculations)
}
