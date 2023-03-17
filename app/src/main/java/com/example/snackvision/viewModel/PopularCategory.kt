package com.example.snackvision.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.snackvision.api.CategoriesApi
import com.example.snackvision.data.PopularCategories
import com.example.snackvision.responseState.ResponseState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularCategory:ViewModel() {

    var responseState :ResponseState by mutableStateOf(ResponseState.Loading)

    init {
        getPopularCategories()
    }

    private fun getPopularCategories() {
        responseState = ResponseState.Loading

       val apiResponse= CategoriesApi.retrofitService.getCategories()

        apiResponse.enqueue(object : Callback<PopularCategories?> {
            override fun onResponse(
                call: Call<PopularCategories?>,
                response: Response<PopularCategories?>
            ) {
                responseState = if (response.isSuccessful){
                    val categories = response.body()?.meals.orEmpty()
                    ResponseState.Success(categories)
                }else{
                    ResponseState.Error
                }
            }

            override fun onFailure(call: Call<PopularCategories?>, t: Throwable) {
                responseState = ResponseState.Error
            }
        })
    }
}



