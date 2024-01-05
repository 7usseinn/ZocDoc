package com.example.zocdoc

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("doc-info")
    fun getDoctors(): Call<List<DoctorDataItem>>
}
