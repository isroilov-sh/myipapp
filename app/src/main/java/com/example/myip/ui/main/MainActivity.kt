package com.example.myip.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myip.R
import com.example.myip.data.network.NetworkApi
import com.example.myip.data.repository.MainRepository
import com.example.myip.ui.main.viewmodel.MainViewModel
import com.example.myip.ui.main.viewmodel.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val retrofitService = NetworkApi.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        viewModel.ip.observe(this, {
            val result = this.getString(R.string.my_ip) + it.ip
            tvIp.text = result
        })

        viewModel.errorMessage.observe(this, {
            tvIp.text = it
        })

        btnGetIp.setOnClickListener { viewModel.getIp() }
    }
}