package com.example.basiccalculator.repository

import androidx.lifecycle.LiveData
import com.example.basiccalculator.Calculations

interface CalcRepository {
    val calcHistory: LiveData<List<Calculations>>

    suspend fun save(historyItem: Calculations)
}