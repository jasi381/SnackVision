package com.example.snackvision.responseState

import com.example.snackvision.data.Meal

sealed interface ResponseState{
    data class Success(val categories :List<Meal>):ResponseState
    object Loading :ResponseState
    object Error:ResponseState
}