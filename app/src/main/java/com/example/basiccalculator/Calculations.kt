package com.example.basiccalculator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Calculations(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val expression: String,
    val date: String,
    val time: String
) {
}