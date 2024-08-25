package com.adminfoodizz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adminfoodizz.adaptar.PendingOrderAdapter
import com.adminfoodizz.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
   private lateinit var binding: ActivityPendingOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }
        val orderCustomerName = arrayListOf(
            "Shriyanshu ",
            "Kaushik",
            "Gaurav",
        )
        val orderedQuantity = arrayListOf(
            "8",
            "6",
            "5",
        )
        val orderedFoodImage = arrayListOf(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3)
        val adapter = PendingOrderAdapter(orderCustomerName,orderedQuantity,orderedFoodImage, this)
        binding.PendingOrderRecyclerView.adapter = adapter
        binding.PendingOrderRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}