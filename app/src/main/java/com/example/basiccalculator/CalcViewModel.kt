package com.example.basiccalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalcViewModel : ViewModel() {

    private val _currentCalcState = MutableLiveData<String>("")
    val currentCalcState: LiveData<String>
        get() = _currentCalcState

    fun updateCalcText(value: String) {
        if (_currentCalcState.value!!.endsWith(Expressions.BUTTON_ADD.value) ||
            (_currentCalcState.value!!.endsWith(Expressions.BUTTON_PERCENT.value)) ||
            (_currentCalcState.value!!.endsWith(Expressions.BUTTON_DIVIDE.value)) ||
            (_currentCalcState.value!!.endsWith(Expressions.BUTTON_MULTIPLY.value)) ||
            (_currentCalcState.value!!.endsWith(Expressions.BUTTON_SUBTRACT.value))
        ) {
            if (value == Expressions.BUTTON_ADD.value ||
                (value == Expressions.BUTTON_PERCENT.value) ||
                (value == Expressions.BUTTON_DIVIDE.value) ||
                (value == Expressions.BUTTON_MULTIPLY.value) ||
                (value == Expressions.BUTTON_SUBTRACT.value)
            ) {
                return
            }
        }

        _currentCalcState.value = _currentCalcState.value?.plus(value)
    }

    fun evaluateResult() {
        val result = ExpressionBuilder(_currentCalcState.value).build().evaluate()
        _currentCalcState.value = result.toInt().toString()
    }

    fun removeAll() {
        _currentCalcState.value = ""
    }

    fun onBackSpaceClicked() {
        _currentCalcState.value = _currentCalcState.value?.dropLast(1) ?: ""
    }
}