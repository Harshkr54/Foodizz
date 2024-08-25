package com.adminfoodizz.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.adminfoodizz.databinding.PendingordersitemBinding

class PendingOrderAdapter(
    private val customerNames: ArrayList<String>,
    private val quantity: ArrayList<String>,
    private val foodImages: ArrayList<Int>,
    private val context: Context
) : RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderAdapter.PendingOrderViewHolder {
        val binding =
            PendingordersitemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PendingOrderViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PendingOrderAdapter.PendingOrderViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = customerNames.size
    inner class PendingOrderViewHolder(private val binding: PendingordersitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isAccpected = false
        fun bind(position: Int) {
            binding.apply {
                customerName.text = customerNames[position]
                Quantity.text = quantity[position]
                orderFoodImage.setImageResource(foodImages[position])
                OrderaccpectButton.apply {
                    if (!isAccpected) {
                        text = "Accpected"
                    } else {
                        text = "Dispatch"
                    }
                    setOnClickListener {
                        if (!isAccpected) {
                            text = "Dispatch"
                            isAccpected = true
                            showToast("Order is Accpected")
                        } else {
                            customerNames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order is Dispatch")
                        }
                    }
                }
            }

        }
       private fun showToast(message: String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }

    }

}