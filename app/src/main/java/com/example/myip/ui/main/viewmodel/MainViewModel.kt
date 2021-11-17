package com.example.myip.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myip.data.model.main.Ip
import com.example.myip.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val ip = MutableLiveData<Ip>()
    val errorMessage = MutableLiveData<String>()

    fun getIp() {

        val response = repository.getAllMovies()
        response.enqueue(object : Callback<Ip> {
            override fun onResponse(call: Call<Ip>, response: Response<Ip>) {
                ip.postValue(response.body())
            }

            override fun onFailure(call: Call<Ip>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}