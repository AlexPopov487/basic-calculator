package com.example.basiccalculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.basiccalculator.databinding.FragmentMainFrBinding


class MainFr : Fragment(), View.OnClickListener {

    private val viewModel: CalcViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val binding = FragmentMainFrBinding.inflate(inflater, container, false)

        binding.btNumber0.setOnClickListener(this)
        binding.btNumber1.setOnClickListener(this)
        binding.btNumber2.setOnClickListener(this)
        binding.btNumber3.setOnClickListener(this)
        binding.btNumber4.setOnClickListener(this)
        binding.btNumber5.setOnClickListener(this)
        binding.btNumber6.setOnClickListener(this)
        binding.btNumber7.setOnClickListener(this)
        binding.btNumber8.setOnClickListener(this)
        binding.btNumber9.setOnClickListener(this)

        binding.btAdd.setOnClickListener(this)
        binding.btBackSpace.setOnClickListener(this)
        binding.btSubstract.setOnClickListener(this)
        binding.btCalculate.setOnClickListener(this)
        binding.btDivide.setOnClickListener(this)
        binding.btInvalidate.setOnClickListener(this)
        binding.btMultiply.setOnClickListener(this)
        binding.btPercent.setOnClickListener(this)


        viewModel.currentCalcState.observe(viewLifecycleOwner, {
            binding.tVCalculations.text = it.toString()
        })

        return binding.root
    }

    override fun onClick(v: View?) {
        val numberClicked = when (v?.id) {
            R.id.bt_number1 -> CalcButton.BUTTON_1.value
            R.id.bt_number2 -> CalcButton.BUTTON_2.value
            R.id.bt_number3 -> CalcButton.BUTTON_3.value
            R.id.bt_number4 -> CalcButton.BUTTON_4.value
            R.id.bt_number5 -> CalcButton.BUTTON_5.value
            R.id.bt_number6 -> CalcButton.BUTTON_6.value
            R.id.bt_number7 -> CalcButton.BUTTON_7.value
            R.id.bt_number8 -> CalcButton.BUTTON_8.value
            R.id.bt_number9 -> CalcButton.BUTTON_9.value
            R.id.bt_number0 -> CalcButton.BUTTON_0.value

            R.id.bt_add -> {
                Expressions.BUTTON_ADD.value
            }
            R.id.bt_substract -> {
                Expressions.BUTTON_SUBTRACT.value
            }
            R.id.bt_multiply -> {
                Expressions.BUTTON_MULTIPLY.value
            }
            R.id.bt_divide -> {
                Expressions.BUTTON_DIVIDE.value
            }
            R.id.percent -> {
                Expressions.BUTTON_PERCENT.value
            }

            R.id.bt_invalidate -> {
                viewModel.removeAll()
                ""
            }
            R.id.bt_backSpace -> {
                viewModel.onBackSpaceClicked()
                ""
            }
            else -> {
                viewModel.evaluateResult()
                ""
            }
        }

        viewModel.updateCalcText(numberClicked.toString())
    }

}