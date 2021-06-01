package com.example.basiccalculator

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basiccalculator.db.AppDb
import com.example.basiccalculator.repository.CalcRepositoryImpl
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class CalcViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CalcRepositoryImpl(AppDb.getInstance(application).dao())


    private val _currentCalcState = MutableLiveData<String>("")
    val currentCalcState: LiveData<String>
        get() = _currentCalcState

    val calcHistory = repository.calcHistory

    fun updateCalcText(value: String) {
        if (_currentCalcState.value!!.endsWith(Operator.BUTTON_ADD.value) ||
            (_currentCalcState.value!!.endsWith(Operator.BUTTON_PERCENT.value)) ||
            (_currentCalcState.value!!.endsWith(Operator.BUTTON_DIVIDE.value)) ||
            (_currentCalcState.value!!.endsWith(Operator.BUTTON_MULTIPLY.value)) ||
            (_currentCalcState.value!!.endsWith(Operator.BUTTON_SUBTRACT.value))
        ) {
            if (value == Operator.BUTTON_ADD.value ||
                (value == Operator.BUTTON_PERCENT.value) ||
                (value == Operator.BUTTON_DIVIDE.value) ||
                (value == Operator.BUTTON_MULTIPLY.value) ||
                (value == Operator.BUTTON_SUBTRACT.value)
            ) {
                return
            }
        }

        _currentCalcState.value = _currentCalcState.value?.plus(value)
    }

    fun evaluateResult() {
        val expressionValue = _currentCalcState.value

        val result = ExpressionBuilder(_currentCalcState.value).build().evaluate()
        _currentCalcState.value = result.toString()

        viewModelScope.launch {
            val dateFormatter = SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss")
            val dateAndTime = dateFormatter.format(Date())
            val date = dateAndTime.split(" ")[0]
            val time = dateAndTime.split(" ")[1]

            val expressionAndResult = "$expressionValue = $result"

            repository.save(
                Calculations(
                    expression = expressionAndResult,
                    date = date,
                    time = time
                )
            )
        }

    }

    fun removeAll() {
        _currentCalcState.value = ""
    }

    fun onBackSpaceClicked() {
        _currentCalcState.value = _currentCalcState.value?.dropLast(1) ?: ""
    }
}