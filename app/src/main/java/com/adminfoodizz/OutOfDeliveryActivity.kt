package com.adminfoodizz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adminfoodizz.adaptar.DeliveryAdaptar
import com.adminfoodizz.databinding.ActivityOutOfDeliveryBinding

class OutOfDeliveryActivity : AppCompatActivity() {
    private val binding : ActivityOutOfDeliveryBinding by lazy {
        ActivityOutOfDeliveryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }
        val customerName = arrayListOf(
            "Shriyanshu ",
            "Kaushik",
            "Gaurav",
        )
        val moneyStatus = arrayListOf(
            "received",
            "Not Received",
            "Pending",
        )
        val adapter = DeliveryAdaptar(customerName,moneyStatus)
        binding.DeliveryRecyclerView.adapter = adapter
        binding.DeliveryRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}