package com.adminfoodizz.adaptar

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adminfoodizz.databinding.DeliveryitemBinding

class DeliveryAdaptar(private val customerNames:ArrayList<String>,private val moneyStatus:ArrayList<String>): RecyclerView.Adapter<DeliveryAdaptar.DeliveryViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeliveryAdaptar.DeliveryViewHolder {
        val binding = DeliveryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DeliveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryAdaptar.DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = customerNames.size
    inner class DeliveryViewHolder(private val binding:DeliveryitemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = customerNames[position]
                statusMoney.text = moneyStatus[position]
                val colorMap = mapOf(
                    "received" to Color.GREEN,
                    "Not Received" to Color.RED,
                    "Pending" to Color.GRAY
                )
                statusMoney.setTextColor(colorMap[moneyStatus[position]]?:Color.BLACK)
                statusColor.backgroundTintList = ColorStateList.valueOf(colorMap[moneyStatus[position]]?:Color.BLACK)
            }
        }

    }

}