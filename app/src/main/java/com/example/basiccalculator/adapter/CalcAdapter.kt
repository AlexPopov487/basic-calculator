package com.example.basiccalculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basiccalculator.databinding.HistoryItemBinding
import com.example.basiccalculator.Calculations

class CalcAdapter :
    androidx.recyclerview.widget.ListAdapter<Calculations, CalcViewHolder>(DiffItemCallback) {

    companion object DiffItemCallback : DiffUtil.ItemCallback<Calculations>() {
        override fun areItemsTheSame(oldItem: Calculations, newItem: Calculations): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Calculations, newItem: Calculations): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalcViewHolder {
        return CalcViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalcViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


}

class CalcViewHolder(private val historyItemBinding: HistoryItemBinding) :
    RecyclerView.ViewHolder(historyItemBinding.root) {
        fun bind(item: Calculations) {
            with(historyItemBinding) {
                tVDate.text = item.date
                tvTime.text = item.time
                tVExpression.text = item.expression
            }


        }
}
