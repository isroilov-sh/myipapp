package com.example.myip.data.repository

import com.example.myip.data.network.NetworkApi

class MainRepository constructor(private val networkApi: NetworkApi) {
    fun getAllMovies() = networkApi.getIp()
}