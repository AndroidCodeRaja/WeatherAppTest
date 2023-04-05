package com.weather.test.model

import java.util.*


data class WeatherInfo(
    val dt: Int,
    val main: Main,
    val weather: ArrayList<Weather>,
    val clouds: Cloud,
    val wind: Wind,
    val sys: Sys,
    val dt_txt: String,
)


data class Sys(
    val pod: String
)


data class Wind(
    val speed: Float, val deg: Float, val gust: Float
)


data class Cloud(
    val all: Float
)


data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)


data class Main(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val sea_level: Float,
    val grnd_level: Float,
    val humidity: Float,
    val temp_kf: Float
)
