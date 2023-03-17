package com.example.snackvision.data

@kotlinx.serialization.Serializable
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)