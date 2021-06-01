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
        val result = ExpressionBuilder(_currentCalcState.value).build().evaluate()
        _currentCalcState.value = result.toString()
    }

    fun removeAll() {
        _currentCalcState.value = ""
    }

    fun onBackSpaceClicked() {
        _currentCalcState.value = _currentCalcState.value?.dropLast(1) ?: ""
    }
}