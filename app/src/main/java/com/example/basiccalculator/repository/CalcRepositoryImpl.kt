package com.example.basiccalculator.repository

import android.webkit.WebHistoryItem
import androidx.lifecycle.LiveData
import com.example.basiccalculator.db.CalcDao
import com.example.basiccalculator.Calculations

class CalcRepositoryImpl(private val dao: CalcDao) : CalcRepository {

    override val calcHistory: LiveData<List<Calculations>>
        get() = dao.getAll()

    override suspend fun save(historyItem: Calculations) {
        dao.save(historyItem)
    }
}