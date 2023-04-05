package com.weather.test.model

import java.util.*


data class City(
    val id: Int,
    val name: String,
    val country: String,
    val population: Int,
    val coord: Coord,
    val timeZone: TimeZone,
    val sunrise: Int,
    val sunset: Int
)


data class Coord(
    val lat: Float,
    val lon: Float
)