package com.example.myip.data.network

import com.example.myip.data.model.main.Ip
import com.example.myip.utils.Constants.URL_BASE
import com.example.myip.utils.Constants.URL_GET_IP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface NetworkApi {


    @GET(URL_GET_IP)
    fun getIp(): Call<Ip>

    companion object {

        private var retrofitService: NetworkApi? = null

        fun getInstance(): NetworkApi {
            if (retrofitService == null) {
                // Create a new object from HttpLoggingInterceptor

                // Create a new object from HttpLoggingInterceptor
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                // Add Interceptor to HttpClient

                // Add Interceptor to HttpClient
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(NetworkApi::class.java)
            }
            return retrofitService!!
        }
    }
}