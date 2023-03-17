package com.example.snackvision.api

import com.example.snackvision.data.PopularCategories
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL ="https://www.themealdb.com/api/json/v1/1/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface FoodApiService {
    @GET("filter.php?c=Dessert")
     fun getCategories(): Call<PopularCategories>
}

object CategoriesApi{
    val retrofitService:FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}