package com.example.medical.client

import com.example.medical.repo.ApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://hospital.elhossiny.net/api/v1/"
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            TokenInterceptorprivate("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZmJlY2I5NTk4NzU1ZjAxZTc2YmE2ZDlkNjBiYzI1YjVhYWY1MzY3Yjc4YTA2MmE5MmNmMmRkNjQ3Mzc1NDg0YzcwZDhhMTZlYWFkMjc0MjYiLCJpYXQiOjE3MzgyNjQ2NzUuNTEzNzU3LCJuYmYiOjE3MzgyNjQ2NzUuNTEzNzU5LCJleHAiOjE3Njk4MDA2NzUuNTEwMjc5LCJzdWIiOiI2MjciLCJzY29wZXMiOltdfQ.D3zrA-xlwtPQOLBALBB10TLF7HYGNei43XpNCpLu-xPxR0ct4EJrOTp7Ij4Ky1Zsfq_2mmkYW4ACwp5lRTAw2IkvAVh8Ki94ZmDAFpFG-hpgS6nd02gFjCWg775KcsORYmqVk0S82nK7GG0hNVFMhlVV_nAb70UY_odPDWNDl-9dFjBJmRPUFguJF968dD222YOS_aQXScGZ4p15rNXcQP9cLvHYPDQ7gjSfLd_uuCJpYtgaoo6LCGVqw-Hcrs0HSQnxAXXk9uHYYF1P9WKbIub1zRtqAyrBguMlaeUaCNGd1dJbgoTlfEZiq2v74How4g9ANkcY14sMoD0bRua2PqELZDCX78TLcvsLs1wPGvCqiu10mwRWv8qVILo4pfqD2Ysp_4QAtc6nYCCfWN7SqsKacgTNOHW3ml5vflSNPSLUpIHciUfUXzsB-m9xi-jBnsg0FIa0HMEe1QqfNkuo2f7hWqHlNYuWElun_KceSeR2QmlRWZVRxhgBb-UJkz6ecnAs7uXZu07y6vB3s4LQWsE6j61udqgF5iOZjys_iiUdP4m2RGZj8iLNwsJT87JhIBsfHnYyNK94G-QRLaEwqBYJvgpD25R05-LC4uscUPZXvvyu8GIptKKsv76ZWhtBeqkwB13Zs5Scuv-IENHCcU5APuGX9q-1frdI_Y5UzBc")
        ) // Pass token
        .build()

    private val gson = GsonBuilder().setLenient().create()

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)
}