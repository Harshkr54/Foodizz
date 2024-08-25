package com.adminfoodizz.adaptar

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adminfoodizz.AllMenu
import com.adminfoodizz.databinding.ItemItemBinding
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference

class MenuItemAdapatar(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    databaseReference: DatabaseReference
) :RecyclerView.Adapter<MenuItemAdapatar.AddItemViewHolder>(){
private val itemQuantities = IntArray(menuList.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AddItemViewHolder {
       val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemAdapatar.AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size

    inner class AddItemViewHolder(private val binding: ItemItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                val menuItem:AllMenu = menuList[position]
                val uriString:String? = menuItem.foodImage
                val uri: Uri = Uri.parse(uriString)
                foodNameTextView.text = menuItem.foodName
                PriceTextView.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(foodImageView)

                quantityTextView.text= quantity.toString()
                minusButton.setOnClickListener {
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener {
                    deleteQuantity(position)
                }

            }
        }
        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position]<10){
                itemQuantities[position]++
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }
        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position]>1){
                itemQuantities[position]--
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }
        private fun deleteQuantity(position: Int) {
            menuList.removeAt(position)
            menuList.removeAt(position)
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,menuList.size)
        }
    }
}