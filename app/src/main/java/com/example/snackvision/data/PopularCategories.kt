package com.example.snackvision.data

@kotlinx.serialization.Serializable
data class PopularCategories(
    val meals: List<Meal>
)