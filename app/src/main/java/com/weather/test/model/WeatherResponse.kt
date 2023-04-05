package com.weather.test.model


data class WeatherResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: ArrayList<WeatherInfo>,
    val city: City
)